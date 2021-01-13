
public enum ItemType
{
    // 랜덤으로 일정 칸 앞으로 / 뒤로, 두 번 굴리기, 적을 움직이지 못하게
    NONE(0), RMOVEUP(1), RMOVEDOWN(2), TWICE(3), FREEZE(4);
    
    int Num;
    
    ItemType(int Num)
    {
        SetType(Num);
    }
    
    void SetType(int Num)
    {
        this.Num = Num;
    }
    
    public static ItemType GetType(int Num)
    {
        for (ItemType Type : values())
        {
            if (Type.Num == Num) return Type;
        }
        throw new IllegalArgumentException("Type Not Found");
    }
}
    