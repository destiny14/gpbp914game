package de.ikarusgames.gpbp914game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ikarusgames.gpbp914game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GPBP914 Game (Desktop)";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new Game(), config);
	}
}
