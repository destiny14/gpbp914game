package de.ikarusgames.gpbp914game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mstam on 05.07.2016.
 */
public class GameScreen implements Screen {

    private final Game m_game;

    private Map<String, GameObject> m_gameObjects;
    private OrthographicCamera m_camera;
    private Ground m_newestGround;

    public GameScreen(final Game _game) {
        m_game = _game;
        m_gameObjects = new HashMap<String, GameObject>();

        m_camera = new OrthographicCamera();
        m_camera.setToOrtho(false, _game.resX, _game.resY);

        Player player = new Player();
        player.init("char.png");
        player.setPosition(200, 400);
        m_gameObjects.put("player", player);

        for(Integer i = 0; i < 6; i++) {
            Ground ground = new Ground(m_camera, this);
            ground.init("ground_grass.png");
            ground.setPosition(200 + i * (ground.getTexture().getWidth() + 300), 250);
            player.addGround(ground);
            m_gameObjects.put("ground" + i.toString(), ground);
            setNewestGround(ground);
        }
    }

    public Ground getNewestGround() {
        return m_newestGround;
    }

    public void setNewestGround(Ground _value) {
        if (_value == null) return;
        m_newestGround = _value;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float camY = m_gameObjects.get("player").getPosition().y < 400 ? 400 : m_gameObjects.get("player").getPosition().y;

        m_camera.position.set(m_gameObjects.get("player").getPosition().x + m_gameObjects.get("player").getTexture().getWidth() / 2,
                camY, m_camera.position.z);
        m_camera.update();

        m_game.batch.setProjectionMatrix(m_camera.combined);
        m_game.batch.begin();

        for (Map.Entry<String, GameObject> entry : m_gameObjects.entrySet()) {
            entry.getValue().render(m_game.batch);
        }

        m_game.batch.end();

        for (Map.Entry<String, GameObject> entry : m_gameObjects.entrySet()) {
            entry.getValue().update(delta);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (Map.Entry<String, GameObject> entry : m_gameObjects.entrySet()) {
            entry.getValue().dispose();
        }
    }
}
