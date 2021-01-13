
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

// 보드 클래스
// 보드 안에는 각 위치에 대한 클래스가 존재
public class Board
{
    
    // 각 위치에 대한 배열
    ArrayList<MoveLocation> BoardSpace;
    
    // 각 위치에 대한 실제 출력상 위치 (콘솔이라 실제론 안 쓰고 초기화만 함)
    ArrayList<Point> SpaceXY;
    
    // 아이템 위치 저장 배열
    Map<Integer, ItemType> ItemLoc;
    
    final int LimitX;
    
    boolean Inited;
    
    Board()
    {
        Inited = false;
        LimitX = 40;
    }
      
       
    // 아이템 생성 여부 설정 (랜덤으로)
    void Init()
    {
        if (Inited) RemoveObject();
        
        Random Rand = new Random(System.currentTimeMillis());
        
        // 먼저 위치를 초기화한다.
        int Num = -1;        
        
        while (true)
        {
            Num = Rand.nextInt(16) + 1; // 1 ~ 16 까지
            
            if (Num >= 6) // 6 이상인 경우만 추출 
            {
                Num *= 5; // 최소 보드 길이는 30, 최대 80 까지 이동 가능
                break;
            }           
        }
               
        if (Num < 0)
        {
            RemoveObject();
            return;            
        }
       
       
        
        SpaceXY = new ArrayList<Point>(Num);
        
        // X, Y 값은 각각 10 씩 증가
        // X 의 최대 위치는 400 (40), Y 는 50 (5) 으로 설정한다. (i 가 40 이 될 때마다 Y 10 증가)
        
        // 보드 칸의 위치 초기화 (실제로는 쓰지 않으니 간단하게)
        
        for (int i = 0; i < Num; i++)
        {
            SpaceXY.add(new Point((i % LimitX) * 10, (i / LimitX) * 10));
        }
        
        
        
        // 먼저 칸 배열들을 초기화한다.
        BoardSpace = new ArrayList<MoveLocation>(Num);

        for (int i = 0; i < Num; i++)
        {
            BoardSpace.add(new MoveLocation(i, SpaceXY.get(i).getLocation(), ItemType.NONE));
        }
        
        
        // 아이템 생성 여부 설정 (랜덤으로)
        // 보드 판의 20% 정도를 아이템 구역으로 정한다.
        ItemLoc = new HashMap<Integer, ItemType>(Num / 5);
        
        
        for (int i = 0; i < (Num / 5); i++)
        {
            while (true)
            {
                int RandLoc = Rand.nextInt(Num);
                MoveLocation RandPos = BoardSpace.get(RandLoc);
                
                if (RandPos.GetItemType() == ItemType.NONE)
                {
                    RandPos.SetItemType(ItemType.GetType(Rand.nextInt(5)));
                    ItemLoc.put(RandLoc, RandPos.GetItemType());
                    break;
                }
            }
        }
           
        
        Inited = true;
    }
    
    void Update()
    {
        // 보드가 움직이거나 상호작용을 하거나 조작할 일은 없으므로 사용할 일 없음        
    }
    
    void Render()
    {
        // 보드를 출력할 일이 없으므로 사용할 일 없음 (플레이어의 정보와 위치만 알려주면 되므로)
    }
    
    void Release()
    {
        
    }
       
    
    
    boolean GetInited()
    {
        return Inited;
    }

    
    void RemoveObject()
    {
        SpaceXY.clear();
        BoardSpace.clear();
        ItemLoc.clear();
        Inited = false;
    }
    
    // 아이템 정보 반환
    Map<Integer, ItemType> GetItemLoc()
    {
        if (ItemLoc.size() > 0 && ItemLoc != null) return ItemLoc;
        else return null;
    }
    
    // 보드판 정보 가져오기
    ArrayList<MoveLocation> GetBoard()
    {
        if (BoardSpace.size() > 0 && BoardSpace != null) return BoardSpace;
        else return null;
    }
    
    // X 영역의 최대 위치 가져오기
    int GetLimitArea()
    {
        return LimitX;
    }
    
}
