import java.awt.Point;

// 주사위 게임 보드판의 각 칸에 대한 클래스

public class MoveLocation
{
    int LocPos; // 보드 판에서의 기준 인덱스 위치 (0 부터 시작) (사실 굳이 필요는 없음)
    Point Location; // 실제 출력상 위치 (콘솔이므로 안 씀)
    int Player; // 해당 칸에 플레이어 있으면 0, 1 로 표시 / 없거나 이동했다면 -1 로 변환 (사실 상호작용을 안 하므로 없어도 상관없음)
    ItemType LocItem; // 아이템 정보
    
    MoveLocation(int LocPos, Point Location, ItemType Item)
    {
        this.LocPos = LocPos;
        this.Location = Location;
        LocItem = Item;
        Player = -1;
    }
    
    ItemType GetItemType()
    {
        return LocItem;
    }
    
    Point GetLoc()
    {
        return Location;
    }
    
    void GetLocPrint()
    {
       System.out.println("[ " + GetLoc().x + " , " + GetLoc().y + " ] "); 
    }
    
    int GetPlayer()
    {
       return Player; 
    }
    
    void SetItemType(ItemType LocItem)
    {
        this.LocItem = LocItem;
    }
    
    void SetPlayer(int Player)
    {
       this.Player = Player;
    }
    
    // 실제론 안 써도 되지만 확실하게 하기 위해
    int GetLocPos()
    {
        return LocPos;
    }
    
    void SetLocPos(int LocPos)
    {
        this.LocPos = LocPos;
    }
    
}