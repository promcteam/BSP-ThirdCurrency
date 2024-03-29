package com.promcteam.genesis.addon.multiplecurrencies;

import java.util.List;

import org.black_ixx.bossshop.managers.ClassManager;
import org.black_ixx.bossshop.managers.features.PointsManager;
import org.black_ixx.bossshop.managers.features.PointsManager.PointsPlugin;
import org.bukkit.configuration.ConfigurationSection;

@SuppressWarnings("unused")
public class CustomPoints {

    private final PointsPlugin pointsplugin;
    private final PointsManager manager;
    private final GenesisPriceTypeThirdCurrencyVariable pricetype;
    private final GenesisRewardTypeThirdCurrencyVariable rewardtype;

    private final String name;
    private final String messageNotEnough;
    private final String messageDisplay;

    private final boolean specialDisplay;
    private final List<String> specialDisplayFormatting;


    public CustomPoints(ConfigurationSection section) {
        name = section.getString("Name");
        pointsplugin = ClassManager.manager.getConfigHandler().findPointsPlugin(section.getString("PointsPlugin"));
        messageNotEnough = section.getString("Message.NotEnoughPoints");
        messageDisplay = section.getString("Placeholder.DisplayPoints");
        specialDisplay = section.getBoolean("PointsDisplay.Enabled");
        specialDisplayFormatting = section.getStringList("PointsDisplay.List");
        manager = new PointsManager(pointsplugin);

        pricetype = new GenesisPriceTypeThirdCurrencyVariable(this);
        rewardtype = new GenesisRewardTypeThirdCurrencyVariable(this);
    }


    public void register() {
        pricetype.register();
        rewardtype.register();
    }


    public String getName() {
        return name;
    }


    public PointsPlugin getPointsPlugin() {
        return pointsplugin;
    }

    public PointsManager getPointsManager() {
        return manager;
    }

    public GenesisPriceTypeThirdCurrencyVariable getPriceType() {
        return pricetype;
    }

    public GenesisRewardTypeThirdCurrencyVariable getRewardType() {
        return rewardtype;
    }

    public String getMessageNotEnoughPoints() {
        return messageNotEnough;
    }

    public String getPlaceholderPoints() {
        return messageDisplay;
    }

    public boolean getSpecialDisplayEnabled() {
        return specialDisplay;
    }

    public List<String> getSpecialDisplayFormatting() {
        return specialDisplayFormatting;
    }

}
