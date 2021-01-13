
public enum PlayerStatus
{
    // 플레이어의 상태 정보
    MOVEABLE(0), UNMOVEABLE(1);
    
    int Num;
    
    PlayerStatus(int Num)
    {
        SetType(Num);
    }
    
    void SetType(int Num)
    {
        this.Num = Num;
    }
    
    public static PlayerStatus GetType(int Num)
    {
        for (PlayerStatus Type : values())
        {
            if (Type.Num == Num) return Type;
        }
        throw new IllegalArgumentException("Type Not Found");
    }
}
    