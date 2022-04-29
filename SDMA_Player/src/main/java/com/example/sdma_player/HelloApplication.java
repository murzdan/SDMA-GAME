
package com.example.sdma_player;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class HelloApplication extends Application {
    int k = 0; //счетчик для спавна теста
    int HP_player = 100;
    int HP_test_1 = 1;
    int HP_test_2 = 1;
    int HP_test_3 = 1;
    int HP_test_4 = 1;
    int HP_test_5 = 1;
    int HP_test_6 = 1;
    int dly = 0;
    int laserio = 0;

    private int angel; //угол

    // Размеры окна
    private static final double W = 806.4, H = 1024;
    // Задание переменной картинки
    private static final String playerImageBlue1 =
            "playerShip1_blue.png";
    // Задание переменной картинки
    private static final String EnemyImage1 =
            "Enemy1.png";

    private static final String EnemyImage2 =
            "Enemy2.png";
    // Задание переменной картинки
    private static final String laserBlue01 =
            "laserBlue01.png";

    private Image EnemyImg1;

    private Image EnemyImg2;

    private Image playerImage;

    private Image laserImage;



    // Переменные для перемещения
    boolean goNorth, goSouth, goEast, goWest;

    Rectangle player = new Rectangle(100,88);
    Rectangle test_1 = new Rectangle(100,88);
    Rectangle test_2 = new Rectangle(100,88);
    Rectangle test_3 = new Rectangle(100,88);
    Rectangle test_4 = new Rectangle(100,88);
    Rectangle test_5 = new Rectangle(100,88);
    Rectangle test_6 = new Rectangle(100,88);


    Group galaxy = new Group();

    @Override
    public void start(Stage stage) throws Exception {

        EnemyImg1 = new Image(EnemyImage1,100,100,true,true);
        EnemyImg2 = new Image(EnemyImage2,100,100,true,true);

        laserImage = new Image(laserBlue01);

        // Создание картинки с размерами, с включением соотношений, с включением сглаживания
        playerImage = new Image(playerImageBlue1,100,100,true,true);

        player.setFill(new ImagePattern(playerImage));

        //Enemy 1
        test_1.setFill(new ImagePattern(EnemyImg1));
        test_2.setFill(new ImagePattern(EnemyImg1));
        test_3.setFill(new ImagePattern(EnemyImg1));

        test_1.setX(50);test_1.setY(10);
        test_2.setX(350);test_2.setY(10);
        test_3.setX(656.4);test_3.setY(10);

        //Enemy 2
        test_4.setFill(new ImagePattern(EnemyImg2));
        test_5.setFill(new ImagePattern(EnemyImg2));
        test_6.setFill(new ImagePattern(EnemyImg2));




        galaxy.getChildren().add(player);
        galaxy.getChildren().addAll(test_1,test_2,test_3);



        movePlayerTo(W / 2, H / 1.2);




        Scene scene = new Scene(galaxy, W, H);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                    case W:
                        goNorth = true; break;
                    case DOWN:
                    case S:
                        goSouth = true; break;
                    case LEFT:
                    case A:
                        goWest  = true; break;
                    case RIGHT:
                    case D:
                        goEast  = true; break;
                    case  SPACE:
                        shoot();


                    }
                        }

        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                    case W:
                        goNorth = false; break;
                    case DOWN:
                    case S:
                        goSouth = false; break;
                    case LEFT:
                    case A:
                        goWest  = false; break;
                    case RIGHT:
                    case D:
                        goEast  = false; break;
                    case SPACE:

                    //  case SHIFT: running = false; break;
                }
            }
        });







        stage.setScene(scene);
        stage.show();





        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 5;
                if (goSouth) dy += 5;
                if (goEast)  dx += 5;
                if (goWest)  dx -= 5;
                //if (running) { dx *= 2; dy *= 2; }
                if (laserio < 600) {
                    laserio ++;
                }
                if (laserio == 600) {
                    laserio = 0;
                }

                Life();
                spawnTest();
                  movePlayerBy(dx, dy);

            }
        };
        timer.start();



    }

    private void Life(){

        if (player.getBoundsInParent().intersects((test_1.getLayoutBounds()))){
            HP_player -= 1;
            HP_test_1 -= 1;
            System.out.println(HP_player);
        }
        if (player.getBoundsInParent().intersects((test_2.getLayoutBounds()))){
            HP_player -= 1;
            HP_test_2 -= 1;
            System.out.println(HP_player);
        }
        if (player.getBoundsInParent().intersects((test_3.getLayoutBounds()))){
            HP_player -= 1;
            HP_test_3 -= 1;
            System.out.println(HP_player);
        }
        if (player.getBoundsInParent().intersects((test_4.getLayoutBounds()))) {
            HP_player -= 1;
            HP_test_4 -= 1;
            System.out.println(HP_player);
        }
        if (player.getBoundsInParent().intersects((test_5.getLayoutBounds()))) {
            HP_player -= 1;
            HP_test_5 -= 1;
            System.out.println(HP_player);
        }
        if (player.getBoundsInParent().intersects((test_6.getLayoutBounds()))) {
            HP_player -= 1;
            HP_test_6 -= 1;
            System.out.println(HP_player);
        }
        if (HP_player == 0){
            galaxy.getChildren().remove(player);
        }
        if (HP_test_1 == 0){
            galaxy.getChildren().remove(test_1);
            test_1.setX(-150);test_1.setY(-150);
        }
        if (HP_test_2 == 0){
            galaxy.getChildren().remove(test_2);
            test_2.setX(-150);test_2.setY(-150);
        }
        if (HP_test_3 == 0){
            galaxy.getChildren().remove(test_3);
            test_3.setX(-150);test_3.setY(-150);
        }
        if (HP_test_4 == 0){
            galaxy.getChildren().remove(test_4);
            test_4.setX(-150);test_4.setY(-150);
        }
        if (HP_test_5 == 0){
            galaxy.getChildren().remove(test_5);
            test_5.setX(-150);test_5.setY(-150);
        }
        if (HP_test_6 == 0){
            galaxy.getChildren().remove(test_6);
            test_6.setX(-150);test_6.setY(-150);
        }

    }

    private void spawnTest(){

        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 0){
            k++;
            test_1.setX(200);test_1.setY(150);
            test_2.setX(525);test_2.setY(150);
            HP_test_1 +=1;
            HP_test_2 +=1;
            galaxy.getChildren().addAll(test_3,test_2,test_1);
        }
        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 1){
            k++;
            test_1.setX(50);test_1.setY(300);
            test_2.setX(350);test_2.setY(300);
            test_3.setX(656.4);test_3.setY(300);
            HP_test_1 +=1;
            HP_test_2 +=1;
            HP_test_3 +=1;
            galaxy.getChildren().addAll(test_3,test_2,test_1);
        }
        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 2){
            k++;
            test_1.setX(200);test_1.setY(450);
            test_2.setX(525);test_2.setY(450);
            HP_test_1 +=1;
            HP_test_2 +=1;
            galaxy.getChildren().addAll(test_3,test_2,test_1);
        }
        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 3){
            k++;
            test_4.setX(50);test_4.setY(10);
            test_5.setX(350);test_5.setY(10);
            test_6.setX(656.4);test_6.setY(10);
            HP_test_4 +=1;
            HP_test_5 +=1;
            HP_test_6 +=1;
            galaxy.getChildren().addAll(test_6,test_5,test_4,test_3,test_2,test_1);
        }
        if (HP_test_4 == HP_test_5 && HP_test_5 == HP_test_6 && HP_test_5 == 0 && k == 4){
            k++;
            test_4.setX(200);test_4.setY(150);
            test_5.setX(525);test_5.setY(150);
            HP_test_4 +=1;
            HP_test_5 +=1;
            galaxy.getChildren().addAll(test_6,test_5,test_4);
        }
        if (HP_test_4 == HP_test_5 && HP_test_5 == HP_test_6 && HP_test_5 == 0 && k == 5){
            k++;
            test_4.setX(50);test_4.setY(300);
            test_5.setX(350);test_5.setY(300);
            test_6.setX(656.4);test_6.setY(300);
            HP_test_4 +=1;
            HP_test_5 +=1;
            HP_test_6 +=1;
            galaxy.getChildren().addAll(test_6,test_5,test_4);
        }
        if (HP_test_4 == HP_test_5 && HP_test_5 == HP_test_6 && HP_test_5 == 0 && k == 6){
            k++;
            test_4.setX(200);test_4.setY(450);
            test_5.setX(525);test_5.setY(450);
            HP_test_4 +=1;
            HP_test_5 +=1;
            galaxy.getChildren().addAll(test_6,test_5,test_4);
        }
    }


    private void shoot() {
        Rectangle laser = new Rectangle(15, 30);
        laser.setFill(new ImagePattern(laserImage));
        galaxy.getChildren().add(laser);
        laser.setLayoutX(player.getLayoutX() + 50);
        laser.setLayoutY(player.getLayoutY() - 30);

        moveshoot(laser);

        }

        private void moveshoot(Rectangle laser){

        laser.setY(laser.getY()-20);

            if (laser.getBoundsInParent().intersects((test_1.getLayoutBounds()))){
                HP_test_1 -= 1;
                galaxy.getChildren().remove(laser);
            }
            if (laser.getBoundsInParent().intersects((test_2.getLayoutBounds()))){
                HP_test_2 -= 1;
                galaxy.getChildren().remove(laser);
            }
            if (laser.getBoundsInParent().intersects((test_3.getLayoutBounds()))){
                HP_test_3 -= 1;
                galaxy.getChildren().remove(laser);
            }
            if (laser.getBoundsInParent().intersects((test_4.getLayoutBounds()))){
                HP_test_4 -= 1;
                galaxy.getChildren().remove(laser);
            }
            if (laser.getBoundsInParent().intersects((test_5.getLayoutBounds()))){
                HP_test_5 -= 1;
                galaxy.getChildren().remove(laser);
            }
            if (laser.getBoundsInParent().intersects((test_6.getLayoutBounds()))){
                HP_test_6 -= 1;
                galaxy.getChildren().remove(laser);
            }
          //  final double cy = laser.getBoundsInLocal().getHeight();
           // double y = cy + laser.getLayoutY() + dly;
           // laser.setY(y - cy);
        }


    private void movePlayerBy(double dx, double dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = player.getBoundsInLocal().getWidth() ;
        final double cy = player.getBoundsInLocal().getHeight();

        double x = cx + player.getLayoutX() + dx;
        double y = cy + player.getLayoutY() + dy;

        if (goWest == true && goEast == false){ //1
            if (angel > -30){
                angel -=5;
            }
            player.setRotate(angel);
            if (player.getLayoutX()>-20){
                player.setLayoutX(player.getLayoutX() -3);
            }
        }
        if (goEast == true && goWest == false){ //2
            if (angel < 30){
                angel +=5;
            }
            player.setRotate(angel);
            if (player.getLayoutX()<522){
                player.setLayoutX(player.getLayoutX() +3);
            }
        }if (goWest == false && goEast == false){ //3
            if (angel < 0){
                angel +=5;
            } else if (angel > 0){
                angel -=5;
            }
            player.setRotate(angel);
        }
        if (goWest == true && goEast == true) { //4
            if (angel < 0) {
                angel += 5;
            } else if (angel > 0) {
                angel -= 5;
            }
            player.setRotate(angel);
        }



      movePlayerTo(x, y);
    }

    private void movePlayerTo(double x, double y) {
        final double cx = player.getBoundsInLocal().getWidth();
        final double cy = player.getBoundsInLocal().getHeight();

        if (x + cx >= 190 &&
                x - cx <= W-100 &&
                y + cy >= 198 &&
                y - cy <= H-100) {
            player.relocate(x - cx , y - cy);
        }
    }

    public static void main(String[] args) { launch(args); }
}