package com.cybersoft.Osahaneat.payload.request;

public class OrderRequest {
    private int userId;
    private int resId;
    private int[] foodId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int[] getFoodId() {
        return foodId;
    }

    public void setFoodId(int[] foodId) {
        this.foodId = foodId;
    }
}
