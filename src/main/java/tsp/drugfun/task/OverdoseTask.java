package tsp.drugfun.task;

import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tsp.drugfun.implementation.item.Drug;

public class OverdoseTask implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int amount = PersistentDataAPI.getInt(player, Drug.DRUG_COUNTER_KEY, -1);
            if (amount <= 0) {
                PersistentDataAPI.remove(player, Drug.DRUG_COUNTER_KEY);
                continue;
            }

            PersistentDataAPI.setInt(player, Drug.DRUG_COUNTER_KEY, amount - 1);
        }
    }

}
