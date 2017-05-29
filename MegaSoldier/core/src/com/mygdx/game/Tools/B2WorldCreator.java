package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MegaSoldier;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Brick;
import com.mygdx.game.Sprites.Objeto;
import com.mygdx.game.Sprites.SoldadoMalo;

/**
 * Created by Karla Rosas on 22/05/2017.
 */

public class B2WorldCreator {
    private Array<SoldadoMalo> enemigos;

    public B2WorldCreator(PlayScreen screen)
    {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //cambiar a cada clase
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Crear fixtures del suelo
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rec.getX() + rec.getWidth()/2) / MegaSoldier.PPM, (rec.getY() + rec.getHeight()/2)/MegaSoldier.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rec.getWidth()/2) / MegaSoldier.PPM, (rec.getHeight()/2)/MegaSoldier.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        //Crear fixtures para tuberia
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rec.getX() + rec.getWidth()/2) /MegaSoldier.PPM, (rec.getY() + rec.getHeight()/2)/MegaSoldier.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rec.getWidth()/2) / MegaSoldier.PPM, (rec.getHeight()/2)/MegaSoldier.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MegaSoldier.OBJECT_BIT;
            body.createFixture(fdef);

        }
        //Ladrillos
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rec);
        }
        //Objetos
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            new Objeto(screen, rec);

        }

        // Enemigos
        enemigos = new Array<SoldadoMalo>();
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rec = ((RectangleMapObject) object).getRectangle();

            enemigos.add(new SoldadoMalo(screen, rec.getX() / MegaSoldier.PPM, rec.getY() / MegaSoldier.PPM));

        }
    }

    public Array<SoldadoMalo> getEnemigos() {
        return enemigos;
    }
}
