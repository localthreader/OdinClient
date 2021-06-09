package net.threader.odinclient.manager;

import net.minecraft.client.MinecraftClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class KeybindManager {

    private Map<Integer, String> keybindMap = new HashMap<>();

    public void loadKeybinds(File file) {
        try (FileReader reader = new FileReader(file)) {
            keybindMap.clear();
            JSONObject jsonResources = (JSONObject) new JSONParser().parse(reader);
            jsonResources.keySet().forEach(obj -> {
                String key = (String) (jsonResources).get(obj);
                String command = (String) jsonResources.get(key);
                keybindMap.put(Integer.parseInt(key), command);
            });

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void onKey(int keyCode) {
        getCommand(keyCode).ifPresent(x -> {
            Objects.requireNonNull(MinecraftClient.getInstance().player, "Player cannot be null");
            MinecraftClient.getInstance().player.sendChatMessage("/" + x);
        });
    }

    private Optional<String> getCommand(int key) {
        return Optional.ofNullable(keybindMap.get(key));
    }
}
