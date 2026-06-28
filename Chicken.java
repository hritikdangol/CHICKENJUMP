class Chicken {
    private double x,y,width,height;
    private boolean isJumping;
    Chicken(double x, double y, double w,double h, boolean isJumping){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
        this.isJumping=isJumping;
    }
    public void jump(){
        isJumping= true;
        System.out.println("JUmped");
    }
    public void land(){
        isJumping=false;
    }
    public void move(double x){
        x++;
    }
    
}
