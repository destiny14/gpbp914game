package de.ikarusgames.gpbp914game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by mstam on 06.07.2016.
 */
public class MainMenuScreen implements Screen {
    private Game m_game;
    private OrthographicCamera m_camera;

    public MainMenuScreen(Game _game) {
        m_game = _game;
        m_camera = new OrthographicCamera();
        m_camera.setToOrtho(false, Game.resX, Game.resY);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        m_camera.update();
        m_game.batch.setProjectionMatrix(m_camera.combined);
        m_game.batch.begin();
        m_game.font.draw(m_game.batch, "Häschen Hüpfen!", 100, Game.resY - 100);
        m_game.font.draw(m_game.batch, "Tippe um zu spielen!", 100, Game.resY - 200);
        m_game.batch.end();

        if (Gdx.input.justTouched()) {
            m_game.setScreen(new GameScreen(m_game));
            dispose();
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
    }
}
