import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

// 플레이어 오브젝트
public class Player
{
    // 턴을 돌리는 것은 게임에서 처리, 턴이 되면 주사위 함수를 실행한다.
    
    String Name; // 이름 정보
    int PlayerNum; // 플레이어 번호
    int MovePos; // 보드에서의 위치
    Point XY; // 실제 위치값
    
    boolean PlayTurn; // 턴이 되었는지를 나타내는 함수
    boolean RollChoice; // 주사위를 굴리기로 했는지 안 했는지 결정
    
    boolean Inited;
    int DiceCount; // 주사위 던진 횟수
    
    
    // 플레이어의 상태를 나타내는 플래그
     PlayerStatus Status;
    
    Player()
    {
        XY = new Point();     
    }
    
    
    void Init(int PNum)
    {
        Name = null;
        MovePos = -1;
        PlayerNum = -1;
        XY.x = -1;
        XY.y = -1;
        PlayTurn = false;
        RollChoice = false;
        DiceCount = -1;
        
        
        Inited = false;
        
        
        // 이름과 번호 등 초기화
        String Name;

        while (true)
        {
            System.out.println("\n\n > 플레이어 " + PNum + " 의  이름을 설정합니다. 이름을 입력하세요.");
            System.out.print(" > 이름 입력 : ");
            Name = Main.Input.nextLine();    

            System.out.println(" \n > 플레이어 이름 : " + Name + " 이(가) 맞습니까? ");
            System.out.print(" > 맞으면 Y 입력 : ");
            String Confirm = Main.Input.nextLine();
            
            if (Confirm.equals("Y") || Confirm.equals("y")) break;
            else System.out.println("\n > 이름을 다시 설정합니다. \n");
        }
        
        this.Name = Name;
        PlayerNum = PNum;
        
        System.out.println("\n\n > 이름과 번호 설정이 완료되었습니다. ( " + this.Name + " / " + PlayerNum + " ) ");
        
        MovePos = 0;
        XY.x = 0;
        XY.y = 0;
        DiceCount = 0;
        Status = PlayerStatus.MOVEABLE;
        
        System.out.println("\n > 초기화가 완료되었습니다.");
        Inited = true;
    }
    
    void Update() // Update 함수는 게임 클래스에 의해 자동으로 실행됨
    {      
        if (Inited) // Rolled = false 라면 주사위를 굴릴 수 있게 됨을 의미
        {
            
            
            if (PlayTurn)
            {
                System.out.println("\n < 플레이어 " + PlayerNum + " : " + Name + " 의  Turn! > ");
                
                if (Status == PlayerStatus.UNMOVEABLE)
                {
                    System.out.println("\n > FREEZE 효과가 적용되어 이동할 수 없습니다!");
                    PlayTurn = false;
                    RollChoice = false;
                    Status = PlayerStatus.MOVEABLE; // 던진 것으로 간주하여 효과 해제
                }
                
                // 주사위 굴리기
                else 
                {
                    // 주사위 굴릴지 여부를 결정
                    RollChoice = DiceRolling();                  
                }
            }          
            
        }
   
    }
    
    void Render()
    {
        // 콘솔인데다 자바라서 버퍼링 등으로 출력할 일이 없으므로 사용 안 함 
        // Update 에서 한번에 처리하는 것이 훨씬 효율적이고 간단함
    }
    
    void Release()
    {
        // 사용 X
    }
    
    
    // 주사위 굴리는 것을 선택하는 것을 플레이어가 하고 실행은 게임이 처리한다.
    boolean DiceRolling()
    {
        String Confirm;

        
        System.out.println(" > 주사위를 굴리려면 '굴리기' 를 입력하세요! / 건너뛰려면 '패스' 를 입력하세요! ");
        
        while (true)
        {
            System.out.print(" > 입력하기 : ");
            Confirm = Main.Input.nextLine();
            if (Confirm.equals("굴리기") || Confirm.equals("패스")) break;
            else System.out.println("\n > 입력이 잘못되었습니다. 다시 입력하세요!\n\n");
        }
        
        if (Confirm.equals("굴리기")) 
        {
            DiceCount++;
            PlayTurn = false;
            return true;        
        }
        
        else if (Confirm.equals("패스")) 
        {
            PlayTurn = false;
            return false;        
        }
        
        // 주사위를 굴리든 말든 진행은 했기 때문에 false 로 변경 (더 이상 기회가 없음을 의미)
        PlayTurn = false;       
        
        return false;
    }
    
    // 굴렸는지 체크
    boolean GetPlayTurn()
    {
        return PlayTurn;
    }   
    
    // PlayTurn 은 자신의 턴이 되었는가를 의미한다. Update 함수로 진행시 false 가 된다.
    void SetPlayTurn(boolean PlayTurn)
    {
        this.PlayTurn = PlayTurn;
    }
    
    // 주사위 던지는 여부를 나타내는 RollChoice 변수
    boolean GetRollChoice()
    {
        return RollChoice;
    }
    
    void SetRollChoice(boolean RollChoice)
    {
        this.RollChoice = RollChoice;
    }
    
    
    // 플레이어 위치 정보
    int GetMovePos()
    {       
        return MovePos;
    }
    
    void SetMovePos(int MovePos)
    {
        this.MovePos = MovePos;
    }
    
    Point GetXY()
    {
        return XY;
    }
    
    void SetXY(Point XY)
    {
        this.XY = XY;
    }
    
    // 초기화 여부
    boolean GetInited()
    {
        return Inited;
    }
    
    // 이름
    String GetName()
    {
        return Name;
    }
    
    // 주사위 굴린 횟수 반환
    int GetDiceCount()
    {
        return DiceCount;
    }
    
    
    // 플레이어의 상태 확인하거나 변경
    PlayerStatus GetStatus()
    {
        return Status;
    }
    
    void SetStatus(PlayerStatus Status)
    {
        this.Status = Status;
    }
    
    
}
