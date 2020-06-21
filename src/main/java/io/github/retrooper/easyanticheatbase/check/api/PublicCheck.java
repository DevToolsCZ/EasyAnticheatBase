package io.github.retrooper.easyanticheatbase.check.api;

import io.github.retrooper.easyanticheatbase.check.api.data.Category;
import io.github.retrooper.easyanticheatbase.events.CheckEvent;
import io.github.retrooper.easyanticheatbase.events.PublicCheckEvent;


public abstract class PublicCheck extends Check {
    public PublicCheck(String name, char checkType, Category category) {
        super(name, checkType, category);
    }

    @Override
    public CheckEvent onPreCheck(CheckEvent e) {
        if(e instanceof PublicCheckEvent) {
            onCheck((PublicCheckEvent)e);
        }
        return e;
    }


    public PublicCheckEvent onCheck(PublicCheckEvent e) {
        return e;
    }
}
