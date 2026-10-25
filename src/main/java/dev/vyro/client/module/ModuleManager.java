package dev.vyro.client.module;

import dev.vyro.client.module.modules.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final List<Module> MODULES = new ArrayList<>();

    public static void init() {
        // Movement
        MODULES.add(new Sprint());
        MODULES.add(new BunnyHop());
        MODULES.add(new Speed());
        MODULES.add(new Spider());
        MODULES.add(new Jesus());
        MODULES.add(new AutoWalk());
        MODULES.add(new AutoJump());
        MODULES.add(new AirJump());
        MODULES.add(new Flight());
        MODULES.add(new Sneak());
        MODULES.add(new Glide());
        MODULES.add(new Jetpack());
        // Combat
        MODULES.add(new AutoTotem());
        MODULES.add(new Triggerbot());
        MODULES.add(new KillAura());
        MODULES.add(new AimAssist());
        MODULES.add(new JumpReset());
        MODULES.add(new InvTotem());
        // Visuals
        MODULES.add(new PlayerESP());
        MODULES.add(new MobESP());
        MODULES.add(new ItemESP());
        MODULES.add(new Glow());
        MODULES.add(new Zoom());
        MODULES.add(new FovBoost());
        MODULES.add(new Coords());
        MODULES.add(new NearbyPlayers());
        MODULES.add(new ArmorInfo());
        MODULES.add(new InfoHud());
        MODULES.add(new HealthWarn());
        MODULES.add(new Crosshair());
        MODULES.add(new Radar());
        MODULES.add(new Keystrokes());
        MODULES.add(new PotionHud());
        MODULES.add(new TotemCount());
        MODULES.add(new Clock());
        MODULES.add(new Compass());
        // World
        MODULES.add(new BaseFinder());
        MODULES.add(new AutoMine());
        MODULES.add(new AntiAFK());
        MODULES.add(new Scaffold());
        MODULES.add(new DeathCoords());
        MODULES.add(new PlayerAlert());
        MODULES.add(new OreFinder());
        MODULES.add(new SpawnerNotifier());
        MODULES.add(new WeatherNotifier());
        MODULES.add(new ChestStealer());
        MODULES.add(new AutoReplenish());
    }

    public static List<Module> byCategory(Module.Category c) {
        List<Module> out = new ArrayList<>();
        for (Module m : MODULES) if (m.category == c) out.add(m);
        return out;
    }

    public static void tick() {
        for (Module m : MODULES) {
            if (m.isEnabled()) m.onTick();
        }
    }
}
