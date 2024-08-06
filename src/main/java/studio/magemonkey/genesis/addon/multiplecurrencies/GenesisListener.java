package studio.magemonkey.genesis.addon.multiplecurrencies;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import studio.magemonkey.genesis.events.GenesisCheckStringForFeaturesEvent;
import studio.magemonkey.genesis.events.GenesisRegisterTypesEvent;
import studio.magemonkey.genesis.events.GenesisTransformStringEvent;
import studio.magemonkey.genesis.misc.MathTools;

@RequiredArgsConstructor
public class GenesisListener implements Listener {
    private final MultipleCurrencies plugin;

    @EventHandler
    public void registerTypes(GenesisRegisterTypesEvent event) {
        if (plugin.getPoints() != null) {
            for (CustomPoints cp : plugin.getPoints()) {
                cp.register();
            }
        }
    }


    @EventHandler
    public void transformString(GenesisTransformStringEvent event) {
        Player p = event.getTarget();
        if (p != null) {

            for (CustomPoints cp : plugin.getPoints()) {
                double points = cp.getPointsManager().getPoints(p);
                String pointsDisplay = MathTools.displayNumber(points,
                        cp.getSpecialDisplayFormatting(),
                        !cp.getPointsManager().usesDoubleValues());
                String text = event.getText().replaceAll("%" + cp.getName() + "%", pointsDisplay);
                event.setText(text);
            }

        }
    }

    @EventHandler
    public void checkString(GenesisCheckStringForFeaturesEvent event) {
        String s = event.getText();

        for (CustomPoints cp : plugin.getPoints()) {
            if (s.contains("%" + cp.getName() + "%")) {
                event.approveFeature();
                break;
            }
        }
    }

}
