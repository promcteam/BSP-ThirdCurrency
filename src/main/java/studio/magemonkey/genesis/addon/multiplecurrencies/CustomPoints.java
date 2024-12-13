package studio.magemonkey.genesis.addon.multiplecurrencies;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import studio.magemonkey.genesis.managers.ClassManager;
import studio.magemonkey.genesis.managers.features.PointsManager;
import studio.magemonkey.genesis.managers.features.PointsManager.PointsPlugin;

import java.util.List;

@SuppressWarnings("unused")
public class CustomPoints {
    @Getter
    private final PointsPlugin                              pointsPlugin;
    @Getter
    private final PointsManager                             pointsManager;
    @Getter
    private final GenesisPriceTypeMultipleCurrencyVariable  priceType;
    @Getter
    private final GenesisRewardTypeMultipleCurrencyVariable rewardType;

    @Getter
    private final String name;
    @Getter
    private final String messageNotEnoughPoints;
    private final String messageDisplay;

    private final boolean      specialDisplay;
    @Getter
    private final List<String> specialDisplayFormatting;


    public CustomPoints(ConfigurationSection section) {
        name = section.getString("Name");
        pointsPlugin = ClassManager.manager.getConfigHandler().findPointsPlugin(section.getString("PointsPlugin"));
        messageNotEnoughPoints = section.getString("Message.NotEnoughPoints");
        messageDisplay = section.getString("Placeholder.DisplayPoints");
        specialDisplay = section.getBoolean("PointsDisplay.Enabled");
        specialDisplayFormatting = section.getStringList("PointsDisplay.List");
        pointsManager = new PointsManager(pointsPlugin);

        priceType = new GenesisPriceTypeMultipleCurrencyVariable(this);
        rewardType = new GenesisRewardTypeMultipleCurrencyVariable(this);
    }


    public void register() {
        priceType.register();
        rewardType.register();
    }


    public String getPlaceholderPoints() {
        return messageDisplay;
    }

    public boolean getSpecialDisplayEnabled() {
        return specialDisplay;
    }

}
