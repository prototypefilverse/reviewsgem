package model;

import dao.MuttersDAO;

public class PostMutterLogic {
	
//  DAO使用に変更	
//	public void execute(Mutter mutter, List<Mutter> mutterList){
//		mutterList.add(0, mutter);  // 先頭に追加
//	}

	public void execute(Mutter mutter){
		MuttersDAO dao = new MuttersDAO();
		dao.create(mutter);
	}
}
