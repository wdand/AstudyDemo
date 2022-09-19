package com.example.studydemo.weight;

public class StepModel  {
    public static final String STATE_PROCESSING="PROCESSING";//正在进行的状态
    public static final String STATE_COMPLETED="COMPLETED";//已经完成的状态
    public static final String STATE_DEFAULT="DEFAULT";//结尾的默认状态
    private String description;//当前状态描述
    private String currentState;//当前状态（上面三个状态中的一个）
    public StepModel(String description, String currentState) {
        this.description = description;
        this.currentState = currentState;
    }
    public String getCurrentState() {
        return currentState;
    }
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
