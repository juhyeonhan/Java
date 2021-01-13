import java.util.Scanner;

public class Main
{
    boolean GameInited;
    DiceGame MyGame = null;
    public static final Scanner Input = new Scanner(System.in);

    
    Main()
    {
        MyGame = new DiceGame();
    }
    
    void Init()
    {
        GameInited = false;
        
        MyGame.Init();
        
        GameInited = true;
    }
    
    void Update()
    {
        if (MyGame != null)
        {
            MyGame.Update(); 
            
            if (MyGame.GetResetFlag())
            {
                MyGame = null; 
                GameInited = false;
            }
        }
        
        else Init();      
    }
    
    void Render()
    {
        if (GameInited == false) 
        {
            System.out.println("\n <주사위 게임을 초기화하고 있습니다>\n ");          
        }
        
        if (MyGame != null)
        {
            MyGame.Render();
        }
        
    }
    
    void Release()
    {
        // 사용 안 함
        MyGame.Release();
    }
    
    public static void main(String[] args)
    {   
        Main MainScene = new Main();
        MainScene.Init();
        
        
        while (true)
        {
          MainScene.Update();
          MainScene.Render();
        }
        
    }
    
}
