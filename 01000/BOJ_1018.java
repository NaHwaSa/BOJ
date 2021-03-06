import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] s = new boolean[n][m];	//B:F, W:T
		boolean[][] wFirstCheck = new boolean[n][m];//F:need change
		boolean[][] bFirstCheck = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {	// input
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				s[i][j] = str.charAt(j)=='W'?true:false;
				
				if ((i + j)%2 == 0 && s[i][j])	// WBWB..와 BWBW.. 체크
					wFirstCheck[i][j] = true;
				else if ((i + j)%2 == 1 && !s[i][j])
					wFirstCheck[i][j] = true;
				if ((i + j)%2 == 0 && !s[i][j])
					bFirstCheck[i][j] = true;
				else if ((i + j)%2 == 1 && s[i][j])
					bFirstCheck[i][j] = true;
			}
		}		
		
		
		/**
		 * 이하 wFirstCheck와 bFirstCheck를 8x8씩 순회하며 최소카운트를 찾아냄.
		 */
		int minCnt = Integer.MAX_VALUE;
		for (int i = 0; i <= n-8; i++) {
			for (int j = 0; j <= m-8; j++) {
				
				int wFirstCnt = 0;
				int bFirstCnt = 0;
				for (int x = i; x < i + 8; x++) {
					for (int y = j; y < j + 8; y++) {
						if (!wFirstCheck[x][y])
							wFirstCnt++;
						if (!bFirstCheck[x][y])
							bFirstCnt++;						
					}					
				}
				
				if (minCnt > wFirstCnt)
					minCnt = wFirstCnt;
				if (minCnt > bFirstCnt)
					minCnt = bFirstCnt;
				
			}
		}
		
		bw.write(minCnt + "\n");
		
		bw.flush();
		br.close();
		bw.close();		
	}	
}
