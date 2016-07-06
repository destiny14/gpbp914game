package de.ikarusgames.gpbp914game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mstam on 05.07.2016.
 */
public class Player extends GameObject {
    private Rectangle m_boundingBox;
    private float m_velocityY = 0;
    private List<Ground> m_grounds;
    private boolean m_isGrounded = false;
    private boolean m_isJumping = false;
    private float m_speed = 250.0f;

    public Player() {
        m_grounds = new ArrayList<Ground>();
    }

    public void addGround(Ground _ground) {
        if (_ground == null) {
            Gdx.app.log("AddGround", "Ground Null");
            return;
        }

        /*Rectangle boundingBox = _ground.getBoundingBox();
        if (boundingBox == null){
            Gdx.app.log("AddGround", "Bounding Box Null");
            return;
        }

        if (m_grounds.contains(boundingBox)){
            Gdx.app.log("AddGround", "Contains Bounding Box");
            return;
        }*/

        if (m_grounds.contains(_ground)) {
            Gdx.app.log("AddGround", "Contains Ground");
            return;
        }

        m_grounds.add(_ground);
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

    public float getSpeed() {
        return m_speed;
    }

    public void setSpeed(float _value) {
        m_speed = _value;
    }

    @Override
    public void init(String _textureName) {
        super.init(_textureName);
        m_boundingBox = new Rectangle();
        m_boundingBox.setHeight(m_texture.getHeight());
        m_boundingBox.setWidth(m_texture.getWidth());
        m_boundingBox.setPosition(m_position);
    }

    public void update(float _delta) {
        super.update(_delta);

        if (m_isJumping) {
            m_isJumping = false;
        }

        if (Gdx.input.justTouched() && m_isGrounded) {
            m_velocityY += 600;
            m_isJumping = true;
        } else {
            m_velocityY -= 9.81f;
        }

        if (!m_isJumping) {
            for (Ground ground : m_grounds) {
                if (m_boundingBox.overlaps(ground.getBoundingBox())) {
                    if (ground.getPosition().y  + ground.getTexture().getHeight() - 25 >= m_position.y){
                        m_isGrounded = false;
                    } else {
                        m_isGrounded = true;
                        m_velocityY = 0;
                        break;
                    }
                } else {
                    m_isGrounded = false;
                }
            }
        }

        m_velocityY = MathUtils.clamp(m_velocityY, -500, 100000);
        m_position.y += m_velocityY * _delta;
        m_position.x += m_speed * _delta;
        m_boundingBox.setPosition(m_position);
    }

    public void render(SpriteBatch _batch) {
        super.render(_batch);
    }

    public void dispose() {
        super.dispose();
    }

}
