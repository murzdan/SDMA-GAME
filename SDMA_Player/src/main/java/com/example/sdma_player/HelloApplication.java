package com.example.sdma_player;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;


public class HelloApplication extends Application {
    int k = 0; //счетчик для спавна теста
    int HP_player = 100;
    int HP_test_1 = 1;
    int HP_test_2 = 1;
    int HP_test_3 = 1;

    private int angel; //угол

    // Размеры окна
    private static final double W = 806.4, H = 1024;
    // Задание переменной картинки
    private static final String playerImageBlue1 =
            "playerShip1_blue.png";
    // Задание переменной картинки
    private static final String EnemyImage1 =
            "Enemy1.png";
    // Задание переменной картинки
    private static final String laserBlue01 =
            "laserBlue01.png";

    private Image EnemyImg1;

    private Image playerImage;

    private Image laserImage;



    // Переменные для перемещения
    boolean goNorth, goSouth, goEast, goWest;

    //Circle player = new Circle(50);
    Rectangle player = new Rectangle(100,88);
    Rectangle test_1 = new Rectangle(100,88);
    Rectangle test_2 = new Rectangle(100,88);
    Rectangle test_3 = new Rectangle(100,88);



    Group galaxy = new Group();

    @Override
    public void start(Stage stage) throws Exception {

        EnemyImg1 = new Image(EnemyImage1,100,100,true,true);

        laserImage = new Image(laserBlue01);

        // Создание картинки с размерами, с включением соотношений, с включением сглаживания
        playerImage = new Image(playerImageBlue1,100,100,true,true);

        player.setFill(new ImagePattern(playerImage));

        test_1.setFill(Color.GREEN);
        test_2.setFill(Color.GREEN);
        test_3.setFill(Color.GREEN);

        test_1.setX(150);test_1.setY(150);
        test_2.setX(300);test_2.setY(150);
        test_3.setX(450);test_3.setY(150);


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


                if (player.getBoundsInParent().intersects((test_1.getLayoutBounds()))){
                    test_1.setFill(Color.RED);
                } else{test_1.setFill(Color.GREEN);}

                if (player.getBoundsInParent().intersects((test_2.getLayoutBounds()))){
                    test_2.setFill(Color.RED);
                } else{test_2.setFill(Color.GREEN);}

                if (player.getBoundsInParent().intersects((test_3.getLayoutBounds()))){
                    test_3.setFill(Color.RED);
                } else{test_3.setFill(Color.GREEN);}

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


    }

    private void spawnTest(){

        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 0){
            k++;
            test_1.setX(150);test_1.setY(150);
            test_2.setX(150);test_2.setY(300);
            test_3.setX(150);test_3.setY(450);
            HP_test_1 +=1;
            HP_test_2 +=1;
            HP_test_3 +=1;
            galaxy.getChildren().addAll(test_3,test_2,test_1);
        }
        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 1){
            k++;
            test_1.setX(246);test_1.setY(734);
            test_2.setX(675);test_2.setY(134);
            test_3.setX(423);test_3.setY(945);
            HP_test_1 +=1;
            HP_test_2 +=1;
            HP_test_3 +=1;
            galaxy.getChildren().addAll(test_3,test_2,test_1);
        }
        if (HP_test_1 == HP_test_2 && HP_test_2 == HP_test_3 && HP_test_2 == 0 && k == 2){
            k++;
            test_1.setX(113);test_1.setY(999);
            test_2.setX(235);test_2.setY(323);
            test_3.setX(150);test_3.setY(463);
            HP_test_1 +=1;
            HP_test_2 +=1;
            HP_test_3 +=1;
            galaxy.getChildren().addAll(test_3,test_2,test_1);
        }
    }


    private void shoot() {
        Rectangle laser = new Rectangle(15, 30);
        laser.setFill(new ImagePattern(laserImage));
        galaxy.getChildren().add(laser);
        laser.setX(player.getLayoutX() + 43);
        laser.setY(player.getLayoutY() - 30);
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

