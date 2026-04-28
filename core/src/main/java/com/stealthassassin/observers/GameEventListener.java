package com.stealthassassin.observers;

public interface GameEventListener {
    void onEvent(String eventType, Object data);
}
