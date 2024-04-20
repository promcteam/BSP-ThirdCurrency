package studio.magemonkey.genesis.addon.multiplecurrencies;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import studio.magemonkey.genesis.api.GenesisAddonConfigurable;

import java.util.ArrayList;
import java.util.List;

public class MultipleCurrencies extends GenesisAddonConfigurable {


    private List<CustomPoints> points;


    @Override
    public String getAddonName() {
        return "MultipleCurrencies";
    }

    @Override
    public String getRequiredGenesisVersion() {
        return "1.0.0";
    }

    @Override
    public void enableAddon() {
        getServer().getPluginManager().registerEvents(new GenesisListener(this), this);

        getConfig().options().copyDefaults(true);
        getConfig().addDefault("1.Name", "token");
        getConfig().addDefault("1.PointsPlugin", "TokenEnchant");
        getConfig().addDefault("1.Message.NotEnoughPoints", "&cYou don't have enough Tokens!");
        getConfig().addDefault("1.Placeholder.DisplayPoints", "%token% Tokens");
        getConfig().addDefault("1.PointsDisplay.Enabled", false);

        List<String> list = new ArrayList<>();
        list.add("1000000:6:2:%number% million");
        list.add("1000:3:2:%number%k");
        list.add("0:0:0:%number%");
        getConfig().addDefault("1.PointsDisplay.List", list);

        getAddonConfig().save();

        load();
    }

    public void load() {
        points = new ArrayList<>();
        for (String key : getConfig().getKeys(false)) {
            ConfigurationSection section = getConfig().getConfigurationSection(key);
            if (section != null) {
                if (section.getString("Name") != null) {
                    CustomPoints cp = new CustomPoints(section);
                    points.add(cp);
                }
            }
        }
    }

    @Override
    public void disableAddon() {
    }

    @Override
    public void genesisFinishedLoading() {
    }

    @Override
    public void genesisReloaded(CommandSender sender) {
        getAddonConfig().reload();
        load();
    }

    @Override
    public boolean saveConfigOnDisable() {
        return false;
    }

    public List<CustomPoints> getPoints() {
        return points;
    }
}
