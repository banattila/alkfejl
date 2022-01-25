package hu.alkfejl;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private static Stage mainWinddow;

    private static Scene actualScene;

    private static Map<String, Scene> scenes = new HashMap<>();

    public static void addScene(String sceneId, Scene scene){
        scenes.put(sceneId, scene);
    }

    public static void setActualScene(String sceneId){
        SceneManager.actualScene = scenes.get(sceneId);
        SceneManager.mainWinddow.setScene(SceneManager.actualScene);
        SceneManager.mainWinddow.setTitle("Actual scene id: " + sceneId);
    }

    public static Scene getActualScene(){
        return SceneManager.actualScene;
    }

    public static void init(Stage mainWinddow){
        SceneManager.mainWinddow = mainWinddow;
    }
}
