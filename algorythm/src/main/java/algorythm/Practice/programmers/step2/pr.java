package algorythm.Practice.programmers.step2;
import java.util.Scanner;

public class pr {
    public static void main(String[] args){
        String choice,errorMessage,wakeUp,faceWash,toilet,washHair,dryHair,wear,stuff;
        String redShirts,yellowShirts,blackShirts,phone,wallet,bag,mask;
        String faceWashText,toiletText,washHairText,dryHairText,milkBreadText,soyMilkText,wearText,stuffText;
        String redShirtsText,yellowShirtsText,blackShirtsText;

        String bathRoom,kitchen,room;

        String whereGoRoomText;

        Scanner sc = new Scanner(System.in);

        wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
        dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";

        errorMessage = "\n### 옳바른 숫자를 입력해 주세요. ###\n";
        whereGoRoomText = "\n어디로 갈까? \n    2.화장실 3.부엌 " ;   //
        faceWashText = "\n깨끗하게 맑게 자신있게~\n";
        toiletText = "\n쾌변~\n";
        washHairText = "\n개운하다~\n";
        dryHairText = "\n좋아 드라이 잘 됐고~\n";
        milkBreadText = "\n오늘은 크림빵 and 서울우유~\n";
        soyMilkText = "\n오늘은 두유에 미숫가루~\n";
        wearText = "\n옷을 입어야겠다~\n" ;
        stuffText = "\n물건을 챙겨야겠고만~\n" ;
        redShirtsText = "\n난 빨간색이 좋더라ㅋ\n";
        yellowShirtsText = "\n난 노란색이 좋더라ㅋ\n";
        blackShirtsText = "\n난 검은색이 좋더라ㅋ\n";

        //상황설명

        System.out.println("\n\n*****************  상황 설명  ********************");
        System.out.println("-------------------------------------------------\n");
        System.out.println("아침에 방에서 일어나서 팀노바로 출발하기까지 준비하는 상황\n");
        System.out.println("-------------------------------------------------");


        //일어나기
        System.out.println("\n알람이 울린다. \n1.알람을 끄고 일어난다.  2.알람을 끄고 더 잔다.");

        choice = sc.nextLine();

        if(choice.equals("1")){
            System.out.println("\n후암~ 잘 잤다.\n");
            wakeUp = "yes";
        }else if(choice.equals("2")){
            System.out.println("\n에라 모르겠다~ 그냥 더 자야징~~\n");
        }else{
            System.out.println(errorMessage);
            wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
            dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
        }

        //일어나서 뭐하는지
        if(wakeUp.equals("yes")) {
            System.out.println(whereGoRoomText);

            choice = sc.nextLine();

            if (choice.equals("2")) {
                bathRoom = "yes";
            } else if (choice.equals("3")) {
                kitchen = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }


        //region 화장실
        //화장실
        if(bathRoom.equals("yes")&&kitchen.equals("no")&&room.equals("no")) {
            System.out.println("\n# 화장실에 들어왔다. #\n\n무엇을 할까? \n1.머리를 감는다. 2.변기에 앉는다. 3.세수를 한다.");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(washHairText);
                washHair = "yes";
            } else if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리를 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n           2.변기에 앉는다. 3.세수를 한다. 4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 머리 말린다
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n            2.변기에 앉는다. 3.세수를 한다.            ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 머리 말린다 -변기에 앉는다
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                         3.세수를 한다.            ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 머리 말린다 -세수한다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n             2.변기에 앉는다.                        ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 세수한다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n             2.변기에 앉는다.             4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            }else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 세수한다.- 변기에 앉는다
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                     4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 세수한다. - 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n             2.변기에 앉는다.                        ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 변기에 앉는다
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                           3.세수를 한다. 4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 변기에 앉는다 - 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                            3.세수를 한다.             ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 변기에 앉는다 - 세수한다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                        4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //화장실 - 변기에 앉는다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n1.머리를 감는다.           3.세수를 한다.");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(washHairText);
                washHair = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 머리 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                           3.세수를 한다. 4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 머리 감는다.- 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                           3.세수를 한다.             ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 머리 감는다.- 세수한다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 세수한다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n 1.머리를 감는다.                                     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(toiletText);
                washHair = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 세수한다.- 머리 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //화장실 - 세수를 한다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n1.머리를 감는다. 2.변기에 앉는다.\"");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(washHairText);
                washHair = "yes";
            } else if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 머리 감는다
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n               2.변기에 앉는다.              4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            }else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 머리 감는다- 변기에 앉는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 머리 감는다- 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n               2.변기에 앉는다.                          ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 변기에 앉는다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n 1.머리를 감는다.                                     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(toiletText);
                washHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 변기에 앉는다.- 머리 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //위치 지정
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("yes")){
            System.out.println("\n어디로 갈까?\n1.방 2.부엌");
            choice = sc.nextLine();

            if (choice.equals("1")) {
                room = "yes";
            }else if (choice.equals("2")) {
                kitchen = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }

        }
        //endregion
        //화장실 - 부엌
        if(bathRoom.equals("yes")&&kitchen.equals("yes")&&room.equals("no")) {
            System.out.println("\n# 부엌으로 왔다. #\n\n무엇을 먹을까? \n1.우유와 함께 빵을 먹는다. 2.두유에 미숫가루를 타먹는다.");
            room = "yes";
            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(milkBreadText);
            }else if (choice.equals("2")) {
                System.out.println(soyMilkText);
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }


        //부엌
        if(bathRoom.equals("no")&&kitchen.equals("yes")&&room.equals("no")) {
            System.out.println("\n# 부엌으로 왔다. #\n\n무엇을 먹을까? \n1.우유와 함께 빵을 먹는다. 2.두유에 미숫가루를 타먹는다.");
            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(milkBreadText);
            }else if (choice.equals("2")) {
                System.out.println(soyMilkText);
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //region 부엌 - 화장실
        //부엌 - 화장실
        if(bathRoom.equals("no")&&kitchen.equals("yes")&&room.equals("no")) {
            System.out.println("\n# 화장실에 들어왔다. #\n\n무엇을 할까? \n1.머리를 감는다. 2.변기에 앉는다. 3.세수를 한다.");
            bathRoom = "yes";room = "yes";
            choice = sc.nextLine();


            if (choice.equals("1")) {
                System.out.println(washHairText);
                washHair = "yes";
            } else if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }

        }
        //화장실 - 머리를 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n           2.변기에 앉는다. 3.세수를 한다. 4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 머리 말린다
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n            2.변기에 앉는다. 3.세수를 한다.            ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 머리 말린다 -변기에 앉는다
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                         3.세수를 한다.            ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 머리 말린다 -세수한다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n             2.변기에 앉는다.                        ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 세수한다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n             2.변기에 앉는다.             4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            }else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 세수한다.- 변기에 앉는다
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                     4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 세수한다. - 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n             2.변기에 앉는다.                        ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 변기에 앉는다
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                           3.세수를 한다. 4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";

            }
        }
        //화장실 - 머리 감는다. - 변기에 앉는다 - 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                            3.세수를 한다.             ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 머리 감는다. - 변기에 앉는다 - 세수한다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                        4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //화장실 - 변기에 앉는다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n1.머리를 감는다.           3.세수를 한다.");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            } else if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 머리 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                           3.세수를 한다. 4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            } else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 머리 감는다.- 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("yes")&&faceWash.equals("no")) {
            System.out.println("\n무엇을 할까? \n                           3.세수를 한다.             ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                System.out.println(faceWashText);
                faceWash = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 머리 감는다.- 세수한다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 세수한다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n 1.머리를 감는다.                                     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(toiletText);
                washHair = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 변기에 앉는다.- 세수한다.- 머리 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //화장실 - 세수를 한다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n1.머리를 감는다. 2.변기에 앉는다.\"");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(washHairText);
                washHair = "yes";
            } else if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 머리 감는다
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n               2.변기에 앉는다.              4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            }else if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 머리 감는다- 변기에 앉는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 머리 감는다- 머리 말린다.
        if(washHair.equals("yes")&&dryHair.equals("yes")&&toilet.equals("no")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n               2.변기에 앉는다.                          ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                System.out.println(toiletText);
                toilet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 변기에 앉는다.
        if(washHair.equals("no")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n 1.머리를 감는다.                                     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(toiletText);
                washHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 세수한다.- 변기에 앉는다.- 머리 감는다.
        if(washHair.equals("yes")&&dryHair.equals("no")&&toilet.equals("yes")&&faceWash.equals("yes")) {
            System.out.println("\n무엇을 할까? \n                                       4.머리를 말린다.");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                System.out.println(dryHairText);
                dryHair = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //endregion

        //region 방
        //화장실 - 방 , 화장실 - 부엌 - 방 , 부엌 - 화장실 - 방 전부 같은 방 공유
        if(room.equals("yes")) {
            System.out.println("\n# 방으로 들어왔다. #\n\n무엇을 할까? \n1.옷을 입는다. 2.물건을 챙긴다.");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(wearText);
                wear = "yes";
            } else if (choice.equals("2")) {
                System.out.println(stuffText);
                stuff = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 옷을 입는다.
        if(wear.equals("yes")&&stuff.equals("no")) {
            System.out.println("\n무엇을 입을까? \n1.빨간 티셔츠에 검은 츄리닝 바지 2.노란 티셔츠에 검은 츄리닝 바지 3.검은 티셔츠에 검은 츄리닝 바지");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(redShirtsText);
                redShirts = "yes";
            } else if (choice.equals("2")) {
                System.out.println(yellowShirtsText);
                yellowShirts = "yes";
            } else if (choice.equals("3")) {
                System.out.println(blackShirtsText);
                blackShirts = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다.
        if(wear.equals("no")&&stuff.equals("yes")) {
            System.out.println("\n무엇을 챙길까? \n1.핸드폰 2.지갑 3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 물건을 챙긴다 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑 3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 지갑 - 가방
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 지갑 - 마스크
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n              3.가방    ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 가방
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑      4.마스크");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 가방 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 가방 - 마스크
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 마스크
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑 3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 마스크 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 마스크 - 가방
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 물건을 챙긴다 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰       3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 핸드폰 - 가방
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                  4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 핸드폰 - 마스크
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방       ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 가방
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰             4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 가방 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                  4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 가방 - 마스크
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                  ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 마스크
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰       3.가방      ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 마스크 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방      ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 마스크 - 가방
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                  ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }


        //방 - 물건을 챙긴다 - 가방
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑     4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑     4.마스크");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 핸드폰 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 핸드폰 - 마스크
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰           4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 지갑 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 지갑 - 마스크
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                 ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 마스크
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑          ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 마스크 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑          ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 마스크 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 물건을 챙긴다 - 마스크
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑 3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑 3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 핸드폰 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 핸드폰 - 가방
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑          ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰       3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 지갑 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n           3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 지갑 - 가방
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 가방
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 가방 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 가방 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                 ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }


        //방 - 물건을 챙긴다 - 옷을 입는다.
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n무엇을 입을까? \n1.빨간 티셔츠에 검은 츄리닝 바지 2.노란 티셔츠에 검은 츄리닝 바지 3.검은 티셔츠에 검은 츄리닝 바지");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(redShirtsText);
                redShirts = "yes";
            } else if (choice.equals("2")) {
                System.out.println(yellowShirtsText);
                yellowShirts = "yes";
            } else if (choice.equals("3")) {
                System.out.println(blackShirtsText);
                blackShirts = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 옷을 입는다 - 물건을 챙긴다.
        if((redShirts.equals("yes")||yellowShirts.equals("yes")||blackShirts.equals("yes"))&&phone.equals("no")) {
            System.out.println("\n무엇을 챙길까? \n1.핸드폰 2.지갑 3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 물건을 챙긴다 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑 3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 지갑 - 가방
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 지갑 - 마스크
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n              3.가방    ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 가방
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑      4.마스크");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 가방 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 가방 - 마스크
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 마스크
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑 3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 마스크 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 핸드폰 - 마스크 - 가방
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 물건을 챙긴다 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰       3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방 4.마스크");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 핸드폰 - 가방
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                  4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 핸드폰 - 마스크
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방       ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 가방
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰             4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 가방 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                  4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 가방 - 마스크
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                  ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 마스크
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰       3.가방      ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 마스크 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방      ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 지갑 - 마스크 - 가방
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                  ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }


        //방 - 물건을 챙긴다 - 가방
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑     4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑     4.마스크");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 핸드폰 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 핸드폰 - 마스크
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰           4.마스크");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 지갑 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("no")) {
            System.out.println("\n더 챙길거 없나??.. \n                4.마스크");

            choice = sc.nextLine();

            if (choice.equals("4")) {
                mask = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 지갑 - 마스크
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                 ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 마스크
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑          ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 마스크 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑          ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 가방 - 마스크 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }

        //방 - 물건을 챙긴다 - 마스크
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑 3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑 3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 핸드폰 - 지갑
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n            3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 핸드폰 - 가방
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑          ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰       3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 지갑 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("yes")&&bag.equals("no")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n           3.가방     ");

            choice = sc.nextLine();

            if (choice.equals("3")) {
                bag = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 지갑 - 가방
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 가방
        if(phone.equals("no")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰 2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            } else if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 가방 - 핸드폰
        if(phone.equals("yes")&&wallet.equals("no")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n      2.지갑           ");

            choice = sc.nextLine();

            if (choice.equals("2")) {
                wallet = "yes";
            } else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //방 - 물건을 챙긴다 - 마스크 - 가방 - 지갑
        if(phone.equals("no")&&wallet.equals("yes")&&bag.equals("yes")&&mask.equals("yes")) {
            System.out.println("\n더 챙길거 없나??.. \n1.핸드폰                 ");

            choice = sc.nextLine();

            if (choice.equals("1")) {
                phone = "yes";
            }  else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
        }
        //화장실 - 부엌 - 방 , 부엌 - 화장실 - 방  끝내기
        if(bathRoom.equals("yes")&&kitchen.equals("yes")&&room.equals("yes")) {
            System.out.println("\n이제 출발해 볼까~?\n1.현관으로 나가기");
            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println("\n출발~\n");
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }

        }
        //endregion

        //화장실 - 방 - 부엌 끝내기
        if(bathRoom.equals("yes")&&kitchen.equals("no")&&room.equals("yes")) {
            System.out.println("\n# 부엌으로 왔다. #\n\n무엇을 먹을까? \n1.우유와 함께 빵을 먹는다. 2.두유에 미숫가루를 타먹는다.");
            kitchen = "yes";
            choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println(milkBreadText);
            }else if (choice.equals("2")) {
                System.out.println(soyMilkText);
            }else {
                System.out.println(errorMessage);
                wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
            }
            if(bathRoom.equals("yes")&&kitchen.equals("yes")&&room.equals("yes")) {
                System.out.println("\n이제 출발해 볼까~?\n1.현관으로 나가기");
                choice = sc.nextLine();

                if (choice.equals("1")) {
                    System.out.println("\n출발~\n");
                } else {
                    System.out.println(errorMessage);
                    wakeUp = faceWash =  toilet =  washHair = bathRoom = kitchen = room = "no";
                    dryHair = wear = stuff = redShirts = yellowShirts = blackShirts = phone = wallet = bag = mask = "no";
                }
            }
        }
    }
}
