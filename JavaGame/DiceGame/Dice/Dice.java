import java.util.Random;

// 주사위 굴리는 클래스 (주사위도 오브젝트이므로)
public class Dice
{
    int DiceNum;
    Random Rand;
    boolean Inited;
    
    Dice()
    {
        Rand = new Random(System.currentTimeMillis());
        Inited = false;
    }
    
    void Init()
    {
        DiceNum = 0;
        Inited = true;
    }
    
    void Update()
    {
        RollDice();
    }
    
    void Render()
    {
        
    }
    
    void Release()
    {
        
    }
    
    void RollDice()
    {
        DiceNum = Rand.nextInt(6) + 1;
    }
    
    int GetDiceNum()
    {
        return DiceNum;
    }
    
    boolean GetInited()
    {
        return Inited;
    }
    
}
