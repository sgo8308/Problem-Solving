package algorythm.Practice.programmers.step3;

public class LockAndKey {
    public boolean solution(int[][] key, int[][] lock) {
    	//lock의 3배 크기에 배열 만들고 lock을 중앙으로 배치
        int[][] board = new int[lock.length * 3][lock.length * 3];
        for(int i = lock.length; i < lock.length * 2; i++){
            for(int j = lock.length; j < lock.length * 2; j++){
                board[i][j] = lock[i - lock.length][j - lock.length];
            }
        }

    	//좌측 상단부터 key를 옮기면서 맞는지 체크
    	//한번씩 돌려가면서 다 체크
        for(int z = 0; z < 4; z++){
            boolean isOk = false;
            for(int i = 0; i < board.length - key.length; i++){
                for(int j = 0; j < board.length - key.length; j++){
                    if(isOk)
                        return true;

                    int[][] newBoard = new int[board.length][board.length];
                    for(int k = 0; k < board.length; k++){
                        for(int l = 0; l < board.length; l++){
                            newBoard[k][l] = board[k][l];
                        }
                    }

                    for(int k = i; k < i + key.length; k++){
                        for(int l = j; l < j + key.length; l++){
                            newBoard[k][l] = newBoard[k][l] + key[k - i][l - j];
                        }
                    }

                    isOk = check(newBoard, lock.length);
                }
            }

            key = rotate(key);
        }


       return false;
    }

    public boolean check(int[][] board, int len){
       for(int k = len; k < len * 2; k++){
           for(int l = len; l < len * 2; l++){
               if(board[k][l] != 1)
                   return false;
           }
       }

       return true;
    }

    public int[][] rotate(int[][] key){
        int[][] newKey = new int[key.length][key.length];
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
        		newKey[i][j] = key[key.length - j - 1][i];
            }
        }

        return newKey;
    }

}
