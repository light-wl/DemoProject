package com.light.designpattern.behavior;

/**
 * @Author light
 * @Date 2023/5/3
 * @Desc 状态模式
 **/
public class StateDesignPattern {
}


interface IMario { //所有状态类的接口
  //以下是定义的事件
  void obtainMushRoom();
  void obtainCape();
  void obtainFireFlower();
  void meetMonster();
}

class SmallMario implements IMario {
  private MarioStateMachine stateMachine;

  public SmallMario(MarioStateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  @Override
  public void obtainMushRoom() {
    stateMachine.setCurrentState(new SuperMario(stateMachine));
    stateMachine.setScore(stateMachine.getScore() + 100);
  }

  @Override
  public void obtainCape() {
    stateMachine.setScore(stateMachine.getScore() + 200);
  }

  @Override
  public void obtainFireFlower() {
    stateMachine.setScore(stateMachine.getScore() + 300);
  }

  @Override
  public void meetMonster() {
    // do nothing...
  }
}

class SuperMario implements IMario {
  private MarioStateMachine stateMachine;

  public SuperMario(MarioStateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  @Override
  public void obtainMushRoom() {
    // do nothing...
  }

  @Override
  public void obtainCape() {
    stateMachine.setScore(stateMachine.getScore() + 200);
  }

  @Override
  public void obtainFireFlower() {
    stateMachine.setScore(stateMachine.getScore() + 300);
  }

  @Override
  public void meetMonster() {
    stateMachine.setCurrentState(new SmallMario(stateMachine));
    stateMachine.setScore(stateMachine.getScore() - 100);
  }
}

// 省略CapeMario、FireMario类...
class MarioStateMachine {
  private int score;
  private IMario currentState; // 不再使用枚举来表示状态

  public MarioStateMachine() {
    this.score = 0;
    this.currentState = new SmallMario(this);
  }

  public void obtainMushRoom() {
    this.currentState.obtainMushRoom();
  }

  public void obtainCape() {
    this.currentState.obtainCape();
  }

  public void obtainFireFlower() {
    this.currentState.obtainFireFlower();
  }

  public void meetMonster() {
    this.currentState.meetMonster();
  }

  public int getScore() {
    return this.score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setCurrentState(IMario currentState) {
    this.currentState = currentState;
  }
}