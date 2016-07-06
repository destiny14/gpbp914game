package de.ikarusgames.gpbp914game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mstam on 05.07.2016.
 */
public class Ground extends GameObject {
    public static float minXDistanceBetweenGrounds = 280;
    public static float maxXDistanceBetweenGrounds = 360;

    public static float minYDistanceBetweenGrounds = 100;
    public static float maxYDistanceBetweenGrounds = 250;

    private Rectangle m_boundingBox;
    private OrthographicCamera m_camera;
    private boolean m_isVisible = false;
    private GameScreen m_gameScreen;

    public Ground(OrthographicCamera _camera, GameScreen _gameScreen) {
        m_camera = _camera;
        m_gameScreen = _gameScreen;
    }

    public void init(String _textureName) {
        super.init(_textureName);
        m_boundingBox = new Rectangle();
        m_boundingBox.setHeight(m_texture.getHeight());
        m_boundingBox.setWidth(m_texture.getWidth());
        m_boundingBox.setPosition(m_position);
    }

    public Rectangle getBoundingBox() {
        return m_boundingBox;
    }

    @Override
    public void setPosition(Vector2 _position) {
        super.setPosition(_position);
        m_boundingBox.setPosition(m_position);
    }

    @Override
    public void setPosition(float _x, float _y) {
        super.setPosition(_x, _y);
        m_boundingBox.setPosition(_x, _y);
    }

    public boolean getIsVisible() {
        return m_isVisible;
    }

    public void update(float _delta) {

        Float distance = m_camera.position.x - (m_position.x + m_texture.getWidth());
        if (distance > Game.resX / 2) {
            m_isVisible = false;
        } else {
            m_isVisible = true;
        }

        if (!m_isVisible) {
            Ground newestGround = m_gameScreen.getNewestGround();
            Vector2 newPos = new Vector2();
            newPos.x = MathUtils.random(newestGround.m_position.x + m_texture.getWidth() + minXDistanceBetweenGrounds,
                    newestGround.m_position.x + m_texture.getWidth() + maxXDistanceBetweenGrounds);
            newPos.y = MathUtils.random(m_camera.position.y - Game.resY / 2,
                    m_camera.position.y + Game.resY / 2);

            newPos.y = MathUtils.clamp(newPos.y,
                    newestGround.m_position.y - maxYDistanceBetweenGrounds,
                    newestGround.m_position.y + maxYDistanceBetweenGrounds);

            m_gameScreen.setNewestGround(this);
            setPosition(newPos);
            m_isVisible = true;
        }

        super.update(_delta);
    }

    public void render(SpriteBatch _batch) {
        super.render(_batch);
    }

    public void dispose() {
        super.dispose();
    }
}
