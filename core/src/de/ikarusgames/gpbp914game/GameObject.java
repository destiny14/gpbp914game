package de.ikarusgames.gpbp914game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mstam on 05.07.2016.
 */
public class GameObject {
    Vector2 m_position;
    Texture m_texture;

    public Vector2 getPosition() {
            return m_position;
    }

    public void setPosition(Vector2 _position) {
        m_position = _position;
    }

    public void setPosition(float _x, float _y) {

        if (m_position == null) {
            m_position.x = _x;
            m_position.y = _y;
        } else {
            m_position = new Vector2(_x, _y);
        }
    }

    public Texture getTexture() {
        return m_texture;
    }

    public void setTexture(Texture _value) {
        m_texture = _value;
    }

    public void init(String _textureName) {
        m_texture = new Texture(_textureName);
        m_position = new Vector2(0,0);
    }

    public void update(float _delta) {

    }

    public void render(SpriteBatch _batch) {
        if (m_texture == null) return;
        Vector2 pos = getPosition();
        _batch.draw(m_texture, pos.x, pos.y);
    }

    public void dispose() {
        m_texture.dispose();
    }
}
