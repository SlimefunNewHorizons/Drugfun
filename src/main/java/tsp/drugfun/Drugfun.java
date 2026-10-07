package tsp.drugfun;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tsp.drugfun.configuration.Config;
import tsp.drugfun.configuration.DrugsConfig;
import tsp.drugfun.implementation.item.Drug;
import tsp.drugfun.implementation.item.Morphine;
import tsp.drugfun.task.OverdoseTask;
import tsp.drugfun.util.Utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

public class Drugfun extends JavaPlugin implements SlimefunAddon {

    private static Drugfun instance;
    private Config config;
    private DrugsConfig drugsConfig;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.config = new Config(new File(getDataFolder(), "config.yml"));
        this.drugsConfig = new DrugsConfig(this.config.getDrugsFile());
        this.drugsConfig.loadYaml(config.isMulti());
        registerDefaults();
        Bukkit.getScheduler().runTaskTimer(this, new OverdoseTask(), 0L, config.getOverdoseExpire() * 20L);
    }

    private void registerDefaults() {
        new Morphine().register(this);
        this.drugsConfig.getDrugs().forEach(data -> {
            Drug drug = new Drug(data);
            drug.register(this);
            Research research = Utils.asResearch(data.getResearchData());
            research.addItems(drug);
            research.register();
        });
    }

    public Config getCfg() {
        return config;
    }

    public DrugsConfig getDrugsConfig() {
        return drugsConfig;
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return "https://github.com/DrakesCraft-Labs/Drugfun/issues";
    }

    public static Drugfun getInstance() {
        return instance;
    }

}
