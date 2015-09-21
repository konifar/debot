package com.tomoima.debot;

import android.content.Context;

import com.tomoima.debot.strategy.CheckAppVersionStrategy;
import com.tomoima.debot.strategy.CheckDpiStrategy;
import com.tomoima.debot.strategy.DebotStrategy;
import com.tomoima.debot.strategy.DebotCallActivityMethodStrategy;
import com.tomoima.debot.strategy.ShowActivityInfoStrategy;

import java.util.ArrayList;

public class DebotConfigurator {

    public static void configureWithDefault(Context context) {
        Debot.initialize(createDefaultMenuConfig(context));
    }

    public static void configureWithCustomizedMenu(Context context, ArrayList<DebotStrategy> customList){
        ArrayList<DebotStrategy> registerList = createDefaultMenuConfig(context);
        registerList.addAll(customList);
        Debot.initialize(registerList);
    }

    private static ArrayList<DebotStrategy> createDefaultMenuConfig(Context context){
        DebotStrategyBuilder builder = new DebotStrategyBuilder.Builder(context)
                .registerMenu("check dpi", new CheckDpiStrategy())
                .registerMenu("show intent", new ShowActivityInfoStrategy())
                .registerMenu("check App ver", new CheckAppVersionStrategy())
                .registerMenu("dev input", new DebotCallActivityMethodStrategy("debugInput"))
                .registerMenu("dev input 2", new DebotCallActivityMethodStrategy("debugInput2"))
                .build();
        return builder.getStrategyList();
    }
}
