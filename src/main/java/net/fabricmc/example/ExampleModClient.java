package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ExampleMod.RICE_COOKER_SCREEN_HANDLER, RiceCookerScreen::new);
        System.out.println("112223333");
    }
}