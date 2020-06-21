package io.github.retrooper.easyanticheatbase.example;

import io.github.retrooper.easyanticheatbase.check.api.Check;
import io.github.retrooper.easyanticheatbase.check.api.data.Category;

public class TestPublicCheck extends Check {
    public TestPublicCheck(String name, char checkType, Category category) {
        super(name, checkType, category);
    }
}
