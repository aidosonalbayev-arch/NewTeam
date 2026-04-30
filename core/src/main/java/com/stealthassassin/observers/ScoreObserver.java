package com.stealthassassin.observers;

import com.stealthassassin.StealthAssassinGame;

public class ScoreObserver implements GameEventListener {

    @Override
    public void onEvent(String eventType, Object data) {
        if ("ENEMY_KILLED".equals(eventType)) {
            String enemyType = (String) data;
            int points = calculatePoints(enemyType);
            StealthAssassinGame.getInstance().addScore(points);
            System.out.println("Esep qosyldy: +" + points + " (" + enemyType + ")");
        } else if ("STEALTH_KILL".equals(eventType)) {
            StealthAssassinGame.getInstance().addScore(50);
            System.out.println("Jasırın óltıru bonusy: +50");
        }
    }

    private int calculatePoints(String enemyType) {
        switch (enemyType) {
            case "Guard":  return 10;
            case "Archer": return 15;
            case "Knight": return 25;
            case "Mage":   return 30;
            case "Boss":   return 100;
            default:       return 5;
        }
    }
}
