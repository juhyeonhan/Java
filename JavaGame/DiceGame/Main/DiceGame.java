import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

// 게임 진행 관련
public class DiceGame
{
    final int MaxPlayer = 2;
    
    Board DiceBoard; // 보드판 오브젝트
    Dice DiceObject; // 주사위 오브젝트 => 플레이어가 '던지기' 를 입력하면 던지기 수행
    Vector<Player> DicePlayers; // 플레이어 배열

    boolean ResetFlag; // ResetFlag True 시, 오브젝트 삭제 후 재시작
    boolean Inited; // 초기화 여부
    
    
    int PlayerTurn; // 0 또는 1 로 턴 변경
    int GameTurn; // 게임의 턴을 의미, 번갈아가며 할 때마다 1회씩 증가
    int TurnOver; // 번갈아 가면서 했는지 여부를 표시 (2회로 증가했다면 GameTurn 이 1 씩 증가)
    int RollNumber; // 주사위 숫자
    boolean EndGame; // 게임 종료 여부를 결정
    int Winner; // 이긴 플레이어의 인덱스 정보
    
    DiceGame()
    {
        DiceBoard = new Board();
        DiceObject = new Dice();
        DicePlayers = new Vector<Player>(MaxPlayer);
    }
    
    void Init()
    {
        Inited = false;
        ResetFlag = false;
        PlayerTurn = -1;
        GameTurn = 0;
        TurnOver = 0;
        RollNumber = -1;
        EndGame = false;
        Winner = -1;

        
        System.out.println(" \n\n < 게임 초기화 > ");
        System.out.println(" > 게임을 초기화합니다. ");

        
        System.out.print("\n > 1. 보드를 초기화합니다. ");
        DiceBoard.Init();
        System.out.println(" => 초기화 완료!");
        
        System.out.print("\n > 2. 주사위를 초기화합니다. ");
        DiceObject.Init();
        System.out.println(" => 초기화 완료!");

        
        System.out.print("\n > 3. 플레이어를 초기화합니다. ");
        
        // 플레이어의 번호 정보를 추가하여 플레이어를 생성
        for (int i = 0; i < MaxPlayer; i++)
        {
            DicePlayers.add(new Player());
            Player PInfo = DicePlayers.get(i);
            PInfo.Init(i + 1);
            
            // 각 플레이어의 첫 위치 값 초기화
            PInfo.SetMovePos(DiceBoard.GetBoard().get(0).GetLocPos());
            PInfo.SetXY(DiceBoard.GetBoard().get(0).GetLoc());
        }
        
        System.out.println(" => 초기화 완료!");
    }
    
    void Update()
    {
        InitCheck();
        
        // 게임이 정상적으로 초기화 된 경우 실행
        if (Inited)
        {
            if (!EndGame)
            {
                
                // 게임 기능 함수들 추가 (플레이어 이름 입력하게 하고 초기화 -> 순서 정하기 -> 게임 진행)
                // 게임 진행은 주사위 오브젝트를 사용하고, 턴제로
                // 주사위에 따라 보드의 플레이어를 이동시키고, 이동 결과에 따라 진행하기
                
                if (PlayerTurn < 0) SetTurn();
                
                // 게임 진행 함수 실행
                StartTurn();
                
                // 턴을 바꾸기 전에 목표 지점에 도착했는지 체크하기
                // 0 이상이라면 도착한 플레이어가 있다는 의미
                Winner = CheckEndGame();
                if (Winner >= 0) EndGame = true;
                
                // 턴 바꾸기
                PlayerTurn = (PlayerTurn == 1) ? 0 : 1;
            }
            
            else
            {
                // 게임 종료시
                System.out.println("\n\n < 게임 종료 : GAME OVER > ");
                System.out.println("\n > 플레이어 " + (Winner + 1) + " 승리!");
                PrintPlayerInfo();
                System.exit(0);
            }
            
        }  
    }
    
    // 업데이트를 할 때는 게임 진행 과정이 한번에 이루어지고, 오브젝트를 스스로 조작하는 기능이 거의 없는데다 (던지는 것 빼고) 실제로 출력하는 그래픽이 없으므로 의미가 없음
    // 따라서 그냥 Update 함수 하나에 모두 넣는다.
    void Render()
    {
    
        
    }
    
    // 사용 X
    void Release()
    {
        
    }
    
    
    // 초기화 체크
    boolean CheckInited()
    {
        if (!DiceBoard.GetInited()) return false;
        else if (!DiceObject.GetInited()) return false;
        
        
        if (!(DicePlayers.size() > 0)) return false;

        if (DicePlayers.size() > 0)
        {
            for (int i = 0; i < MaxPlayer; i++)
            {
                if (!(DicePlayers.get(i).GetInited())) return false;               
            }           
        }
        
        return true;
    }
    
    void InitCheck()
    {
        if (CheckInited())
        {
            if (!Inited)
            {
                System.out.println("\n > 게임 초기화 완료\n");
                PrintBoardInfo();
                PrintPlayerInfo();
                
                Inited = true;   
            }
        }
        
        else    
        {      
            System.out.println("\n > 게임 초기화 실패! 게임을 다시 실행합니다. \n");
            
            try
            {
                Thread.sleep(2000);
                ResetFlag = true;
            }
            
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }  
    
    
    // 오브젝트 삭제 알림 플래그
    boolean GetResetFlag()
    {
        return ResetFlag;
    }
    
    
    void PrintBoardInfo()
    {
        // 보드의 내부 정보
        ArrayList<MoveLocation> Board = DiceBoard.GetBoard();
        int LimitArea = DiceBoard.GetLimitArea(); // X축 최대 인덱스 위치
                 
        System.out.println("\n > < 보드 정보 >");
        System.out.println(" > 1. 보드 크기 : " + Board.size());
        System.out.print(" > 2. 시작 위치 : ");
        Board.get(0).GetLocPrint();
        System.out.print(" > 3. 끝 위치 : ");
        Board.get(Board.size() - 1).GetLocPrint();
        System.out.println(" > 4. 좌표 위치 간격 : " + (Board.get(1).GetLoc().x - Board.get(0).GetLoc().x));
        System.out.println(" > 5. X 의 최대 가능 인덱스는  " + LimitArea + " 이며, X 의 최대 가능 위치는 " 
        + (LimitArea - 1) * 10 + " 입니다.\n");
        
    }
    
    void PrintPlayerInfo()
    {
        // 플레이어 정보
        System.out.println("\n > < 플레이어 정보 >");
        for (int i = 0; i < MaxPlayer; i++)
        {
            Player PInfo = DicePlayers.get(i);
            System.out.println(" > 플레이어 " + (i + 1) + " : " + PInfo.GetName() + " (" + " 위치 : " + (PInfo.GetMovePos() + 1) + " / " + DiceBoard.GetBoard().size() + 
                               " , " + "주사위 굴린 횟수 : " + PInfo.GetDiceCount() + " 회  ) ");
        }
        
        System.out.println("\n");
    }
    
    
    // 순서 정하기
    void SetTurn()
    {
        System.out.println("\n\n < 시작 순서 결정 >");
        System.out.println(" > 게임의 시작 순서는 랜덤으로 결정됩니다.");
        
        System.out.println("\n > 순서 결정 중...");
        
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                
        Random Rand = new Random(System.currentTimeMillis());
        int Choice = Rand.nextInt(2);

        System.out.println("\n > 순서 결정을 완료했습니다. " + (Choice + 1) + " 번째 플레이어 ( 이름 : " + DicePlayers.get(Choice).GetName() + " ) 가 먼저 시작합니다.");
        PlayerTurn = Choice;
    }
    
    
    // 핵심 게임 함수
    void StartTurn()
    {
        Player PInfo = DicePlayers.get(PlayerTurn);
        ArrayList<MoveLocation> BPosInfo = DiceBoard.GetBoard();

        // 먼저 게임 정보를 알리고, 순서를 알린 뒤 던지도록 한다.
        System.out.println("\n\n < CURRENT GAME COUNT : " + GameTurn + " > ");
        System.out.println(" > 현재 턴 : 플레이어 " + (PlayerTurn + 1) + " ( " + PInfo.GetName() + " / " + "주사위 굴린 횟수 : " + PInfo.GetDiceCount() + " 회  ) ");
        System.out.println(" > 현재 위치 : " + (PInfo.GetMovePos() + 1) + " / " + DiceBoard.GetBoard().size() + " , " + "실제 위치 : " + PInfo.GetXY().x + " , " + PInfo.GetXY().y);
        
        // 주사위를 굴린다 -> 던져서 나온 숫자에 따라 지정된 칸으로 이동한다 -> 아이템을 확인한다.
        // 그런데 만약 TWICE 등의 아이템이 나와서 2~3번 이상 하게 된다면 반복 실행이 필요하므로 while 문으로 처리
        // TWICE 아이템이 안 나왔거나, 주사위 굴리기를 포기한 경우에는 break
        while (true)
        {
            boolean EndTurn = false; // True 시 턴 종료를 알리게 됨
            
            // 플레이어를 실행하여 주사위를 굴리게 될지 여부를 묻는다.
            PInfo.SetPlayTurn(true); // PlayTurn 은 해당 플레이어가 주사위를 굴릴 수 있음을 알려준다.
            PInfo.SetRollChoice(false);
            
            PInfo.Update(); // PlayTurn true 시 동작하며, 진행을 하면 false 가 된다.
            
            // 주사위를 던지는 경우
            if (PInfo.GetRollChoice())
            {
                // 여기부터는 주사위를 이용하여 진행
                RollNumber = RollDice();
                System.out.println("\n > 주사위 숫자는 " + RollNumber + "! \n");
                
                // 이동 함수를 이용하여 이동
                MovePosition(PInfo, RollNumber, false);
                
                // 주사위를 던져 이동하는 경우 최소 1회의 아이템 체크를 하게 된다.
                boolean NeedItemCheck = true; // 아이템 체크가 필요한 경우 true 가 된다.
                
                while (true)
                {                    
                // 해당 위치에 아이템이 있는지 체크 후 효과 적용하기 => 함수에서 하지 않고 여기에서 수행
                ItemType Type = BPosInfo.get(PInfo.GetMovePos()).GetItemType();
                
                    switch (Type)
                    {
                        case RMOVEUP:
                        {
                            Random Rand = new Random(System.currentTimeMillis());
                            System.out.println("\n > RMOVEUP 아이템 발견! : 랜덤으로 1 ~ 5 칸 앞으로 이동합니다!");
                            
                            int RandNum = Rand.nextInt(5) + 1;
                            MovePosition(PInfo, RandNum, false);
                            // 이 경우 NeedItemCheck 는 false 가 되면 안 된다. 다른 위치로 이동했으므로 다시 확인해야 하기 때문
                            EndTurn = true;
                            break;
                        }
                        
                        case RMOVEDOWN:
                        {
                            Random Rand = new Random(System.currentTimeMillis());
                            System.out.println("\n > RMOVEDOWN 아이템 발견! : 랜덤으로 1 ~ 5 칸 뒤로 이동합니다!");
                            
                            int RandNum = Rand.nextInt(5) + 1;
                            MovePosition(PInfo, RandNum, true);
                            // 마찬가지로 NeedItemCheck 는 false 가 되면 안 된다. 다른 위치로 이동했으므로 다시 확인해야 하기 때문
                            EndTurn = true;
                            break;
                        }
                        
                        case TWICE:
                        {
                            // 두 번 던지기
                            System.out
                                    .println("\n > TWICE 아이템 발견! : 한 번 더 주사위를 던집니다!");
                            // EndTurn 그대로 유지
                            
                            // 이 경우에는  이동을 하지만 주사위를 굴린 뒤 결정하므로 false 처리하여 아이템 확인 반복문을 빠져나온다.
                            NeedItemCheck = false;
                            break;
                        }
                        
                        case FREEZE:
                        {
                            // 상대방의 턴을 막기
                            System.out.println("\n > FREEZE 아이템 발견! : 상대방의 턴을 무시합니다!");
                            int OtherPlayer = (PlayerTurn == 1) ? 0 : 1; // 0 또는 1 이므로 이렇게 수정
                            DicePlayers.get(OtherPlayer).SetStatus(PlayerStatus.UNMOVEABLE); // 움직이지 못하게
                            EndTurn = true;
                            
                            // 이 경우에는 이동을 하지 않으므로 false 처리하여 아이템 확인 반복문을 빠져나온다.
                            NeedItemCheck = false;
                            break;
                        }
                        
                        default: // 아무것도 없다면 그냥 넘기기
                        {
                            EndTurn = true;
                            NeedItemCheck = false; // 마찬가지로 false 처리한다.
                            break;
                        }    
                    }
                    
                    
                    // 더 이상 아이템 체크가 필요없다면 break 하여 빠져나온다.
                    if (!NeedItemCheck) break;
                    
                } // 아이템 체크 while 문 종료
   
            } // 아이템까지 적용 완료 (if 문 종료)
            
            else break; // 만약 주사위를 안 던진다면 break 하도록 하여 턴을 종료한다.
            
            // EndTurn 에 따라 종료 여부를 결정한다. TWICE 로 한 번 더 하는 경우 EndTurn 은 true 가 되지 않는다.
            if (EndTurn) break;       
        }
        
        // 턴이 종료되었으니 메시지로 알리고 턴 변경
        System.out.println("\n\n > 현재 턴 : 플레이어 " + (PlayerTurn + 1) + " ( " + PInfo.GetName() + " ) 의 턴이 종료되었습니다!");
        System.out.println(" > 다음 턴으로 이동합니다.");
        
        // 턴을 종료했으므로 횟수를 기록
        // 보통 2턴을 한 세트로 치며, 2턴이 되면 GameTurn 이 증가 
        if (++TurnOver >= 2) 
        {
            GameTurn++;
            TurnOver = 0;
        }   
        else TurnOver++;
        
        
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
    }
    
    int RollDice()
    {
        DiceObject.Update();
        return DiceObject.GetDiceNum();
    }
    
    
    // 플레이어 위치 이동시키기
    void MovePosition(Player PInfo, int RollNumber, boolean MoveDown)
    {
        int BeforePos = PInfo.GetMovePos();
        Point BeforeXY = PInfo.GetXY();
       
        
        ArrayList<MoveLocation> BPosInfo = DiceBoard.GetBoard();
        
        if (MoveDown) 
        {
            RollNumber *= -1;       
        }
        
        int AfterPos = PInfo.GetMovePos() + RollNumber;
        
        // 만약 이동하여 경계 위치를 넘어갔다면 수정해 준다.
        if (AfterPos < 0) AfterPos = 0;
        else if (AfterPos > (BPosInfo.size() - 1)) AfterPos = BPosInfo.size() - 1;
        
        // 현재 위치에서 주사위 숫자만큼 떨어진 곳으로 좌표를 이동한다.
        PInfo.SetMovePos(AfterPos);
        PInfo.SetXY(BPosInfo.get(PInfo.GetMovePos()).GetLoc()); 
        
        // 이동 완료를 표시
        System.out.println("\n > 플레이어  : " + PInfo.GetName() + " 이(가) 이동을 완료했습니다!");
        System.out.println(" > 이동 이전 : " + (BeforePos + 1) + " / " + DiceBoard.GetBoard().size() + " , " + "실제 위치 : " + BeforeXY.x + " , " + BeforeXY.y);
        System.out.println(" > 이동 이후 : " + (PInfo.GetMovePos() + 1) + " / " + DiceBoard.GetBoard().size() + " , " + "실제 위치 : " + PInfo.GetXY().x + " , " + PInfo.GetXY().y);
        
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    // 게임이 끝났는지 체크하고 끝났다면 이긴 플레이어의 인덱스를 반환
    int CheckEndGame()
    {
        int WinPlayer = -1;
        ArrayList<MoveLocation> BPosInfo = DiceBoard.GetBoard();
        
        for (int i = 0; i < MaxPlayer; i++)
        {
            // 끝 지점에 도착한 플레이어가 있다면 알려준다.
            if (DicePlayers.get(i).GetMovePos() == BPosInfo.get(BPosInfo.size() - 1).GetLocPos())
            {
                WinPlayer = i;
            }
        }
        
        return WinPlayer;
    }
    
}
