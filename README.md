# Stealth Assassin

> A 2D stealth-based RPG game built with libGDX, demonstrating 10 software design patterns.

![Java](https://img.shields.io/badge/Java-8+-orange)
![libGDX](https://img.shields.io/badge/libGDX-1.14.0-red)
![License](https://img.shields.io/badge/License-Educational-blue)
![Status](https://img.shields.io/badge/Status-Complete-green)

---

## About the Game

Stealth Assassin is a top-down 2D stealth-action game where the player chooses one of three unique heroes — **Ninja**, **Shadow**, or **Rogue** — and must eliminate enemies using stealth, strategy, and special abilities. Each enemy has its own AI behavior controlled through the **State design pattern**: Patrol → Chase → Attack → Search.

Tagline: Shadows Never Forgive

---

## Controls

| Key             | Action                        |
| --------------- | ----------------------------- |
| `W` `A` `S` `D` | Move hero                     |
| `SPACE`         | Activate special ability      |
| `F`             | Attack closest enemy in range |
| `ENTER`         | Confirm selection             |
| `ESC`           | Return to menu / quit         |
| `1` `2` `3`     | Quick hero selection          |
| `E` / `H`       | Choose Easy / Hard difficulty |

---

## Heroes

| Hero       | HP  | Speed | Stealth | Damage | Special Ability                  |
| ---------- | --- | ----- | ------- | ------ | -------------------------------- |
| **Ninja**  | 250 | 200   | 0.9     | 30     | Shadow Strike (damage x2 for 5s) |
| **Shadow** | 150 | 180   | 1.0     | 25     | Invisibility (5s)                |
| **Rogue**  | 200 | 150   | 0.7     | 45     | Iron Skin (+50 max HP, one-time) |

---

## Enemies

| Enemy      | HP  | Damage | Detection | Score | Special      |
| ---------- | --- | ------ | --------- | ----- | ------------ |
| **Guard**  | 50  | 15     | 150 px    | +10   | Basic patrol |
| **Archer** | 40  | 20     | 250 px    | +15   | Long range   |
| **Knight** | 120 | 30     | 180 px    | +25   | 30% armor    |
| **Mage**   | 60  | 35     | 280 px    | +30   | Magic attack |
| **Boss**   | 300 | 60     | 400 px    | +100  | Multi-phase  |

---

## Design Patterns Used (10 Total)

This project demonstrates all 10 GoF design patterns required by the SDP course:

| #   | Pattern              | Where Applied                                                           |
| --- | -------------------- | ----------------------------------------------------------------------- |
| 1   | **Singleton**        | `StealthAssassinGame`, `AssetManager`, `GameEventManager`, `BulletPool` |
| 2   | **Factory Method**   | `HeroFactory`, `EnemyFactory`                                           |
| 3   | **Abstract Factory** | `AbstractGameFactory` + `EasyLevelFactory` / `HardLevelFactory`         |
| 4   | **Builder**          | `LevelBuilder` — step-by-step level construction                        |
| 5   | **Prototype**        | `Enemy.clone()` — cloning enemies for spawn                             |
| 6   | **Object Pool**      | `BulletPool` — reusing Bullet objects                                   |
| 7   | **Decorator**        | `HeroDecorator` + Stealth/Speed/Damage Boost                            |
| 8   | **Facade**           | `GameFacade` — simplifies game subsystems                               |
| 9   | **State**            | `EnemyState` + Patrol/Chase/Attack/Search                               |
| 10  | **Observer**         | `GameEventManager` + `ScoreObserver`                                    |

---

## SOLID Principles

This project strictly follows all five SOLID principles:

### S — Single Responsibility

Each class does **one job**:

- `InputManager` → only input handling
- `DetectionManager` → only stealth detection logic
- `ScoreObserver` → only score tracking
- `AssetManager` → only resource caching

### O — Open / Closed

Adding a new enemy or hero **does not require changing existing code**:

- New enemy = create class extending `Enemy`
- New hero = create class extending `Hero`
- New AI state = create class implementing `EnemyState`

### L — Liskov Substitution

All subclasses are interchangeable with their parents:

```java
Hero hero = new Ninja();
Hero hero = new Shadow();
Hero hero = new Rogue();
```

### I — Interface Segregation

Interfaces are **small and focused**:

- `EnemyState` → only `handle()` and `getStateName()`
- `GameEventListener` → only `onEvent()`

### D — Dependency Inversion

Code depends on **abstractions**, not concrete classes:

- `GameFacade` works with abstract `AbstractGameFactory`
- `Enemy.currentState` is `EnemyState` (interface), not specific state
- `HeroDecorator` wraps abstract `Hero`, not specific hero type

---

## Project Structure

```
core/src/main/java/com/stealthassassin/
├── StealthAssassinGame.java          [Singleton]
├── builders/
│   ├── Level.java
│   └── LevelBuilder.java             [Builder]
├── decorators/                       [Decorator]
│   ├── HeroDecorator.java
│   ├── DamageBoostDecorator.java
│   ├── SpeedBoostDecorator.java
│   └── StealthBoostDecorator.java
├── entities/
│   ├── Entity.java                   [abstract]
│   ├── heroes/
│   │   ├── Hero.java                 [abstract]
│   │   ├── Ninja.java
│   │   ├── Shadow.java
│   │   └── Rogue.java
│   └── enemies/
│       ├── Enemy.java                [abstract + Prototype]
│       ├── Guard.java
│       ├── Archer.java
│       ├── Knight.java
│       ├── Mage.java
│       └── Boss.java
├── facade/
│   └── GameFacade.java               [Facade]
├── factories/                        [Factory Method + Abstract Factory]
│   ├── HeroFactory.java
│   ├── EnemyFactory.java
│   ├── AbstractGameFactory.java
│   ├── EasyLevelFactory.java
│   └── HardLevelFactory.java
├── managers/
│   ├── AssetManager.java             [Singleton]
│   ├── DetectionManager.java
│   └── InputManager.java
├── map/
│   └── GameMap.java
├── observers/                        [Observer]
│   ├── GameEventListener.java
│   ├── GameEventManager.java
│   └── ScoreObserver.java
├── pool/
│   └── BulletPool.java               [Object Pool]
├── screens/
│   ├── MainMenuScreen.java
│   ├── HeroSelectionScreen.java
│   ├── GameScreen.java
│   └── GameOverScreen.java
├── states/                           [State]
│   ├── EnemyState.java
│   ├── PatrolState.java
│   ├── ChaseState.java
│   ├── AttackState.java
│   └── SearchState.java
└── utils/
    ├── Bullet.java
    ├── Vector2D.java
    └── TextureGenerator.java
```

---

## How to Run

### Requirements

- Java 8 or higher
- IntelliJ IDEA (recommended) or any IDE with Gradle support

### Steps

1. Clone the repository:
   ```bash
   https://github.com/aidosonalbayev-arch/NewTeam.git
   ```
2. Open the project in IntelliJ IDEA
3. Wait for Gradle to sync dependencies
4. Run `Lwjgl3Launcher.java` (in `lwjgl3/src/main/java/.../lwjgl3/`)

### Build a runnable JAR

```bash
./gradlew lwjgl3:dist
```

The JAR will be located in `lwjgl3/build/libs/`.

---

## Team

| Member                | Role                           | Responsibilities                                                                                                        |
| --------------------- | ------------------------------ | ----------------------------------------------------------------------------------------------------------------------- |
| **Onalbaev Aidos**    | Project Lead & Lead Programmer | ClickUp board, deadlines, code reviews, core architecture (`StealthAssassinGame`, `GameFacade`), all Screens, UI design |
| **Seisembay Asylbek** | Programmer                     | Hero classes (Ninja/Shadow/Rogue), Enemy classes (Guard/Archer/Knight/Mage/Boss), Factory patterns                      |
| **Aruzhan Kuat**      | Programmer & Designer          | State pattern (4 states), Observer pattern, Decorator pattern, Object Pool, Vector2D, TextureGenerator                  |

---

## Documentation

Full project documentation is located in the `docs/` folder:

- [Game Design Document (PDF)](docs/StealthAssassin_GDD.pdf)
- ️[Game Flow Diagram (SVG)](docs/01_GameFlowDiagram.svg)
- ️[Class Diagram (SVG)](docs/02_ClassDiagram.svg)
- [Level Sketch (SVG)](docs/03_LevelSketch.svg)

---

## Known Issues

- _No known critical issues at the time of release._

---

## Acknowledgments

- Built with **libGDX** — https://libgdx.com
- Inspired by classic stealth games (Mark of the Ninja, Hotline Miami)
- Course: **Software Design Patterns (SDP)** — 2nd Year, 2026

---

## License

This project is created for educational purposes as part of the SDP course at NARXOZ University.

---

_Made with by Aidos, Asylbek & Aruzhan • 2026_
