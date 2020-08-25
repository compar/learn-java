package compar.demo.java8fun;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statements;

public class ParserDemo {
	public static void main(String[] args) {
		try {
			Statements stmt = CCJSqlParserUtil.parseStatements("select row_.*, rownum rownum_ from ( select * from ( SELECT * FROM (select (select count(1) \n" + 
					"from t_uploader_blob fj where fj.business_id=wz.id and fj.business_type='XXFB_GAYQ1') as fjsl,(select \n" + 
					"count(1) from t_xxfb_qy where xxfbid=wz.id and SYRID='4028803b20f5e19e0120f5e42a890002')as \n" + 
					"isqy, wz.* from( select wz2.* from T_XXFB_WZ wz2,t_XXFB_lm lm where wz2.lmid=lm.id and lm.tzbs=10 \n" + 
					"and wz2.lmid='XXFB_GAYQ1' and wz2.deleted=0 ) wz ) t1 INNER JOIN (select d.lj, t.* from (select \n" + 
					"id as gayqid, regexp_substr(jsyhs, '[^,]+', 1, level) as jsyhs from t_xxfb_gayq connect by \n" + 
					"level <= regexp_count(jsyhs, ',') + 1 and id = prior id ) t, dm_dept d where d.bh0000 = t.jsyhs and instr((SELECT D.LJ FROM DM_DEPT D WHERE D.BH0000 \n" + 
					"='640000000000'), d.lj) > 0) t2 on T1.id = T2.gayqid ) st_ where 1=1 and createTime >= '20191007000000' \n" + 
					"and createTime <= '20191107235959' and lmid = 'XXFB_GAYQ1' order by createTime DESC ) row_ \n" + 
					"where rownum <= 10 ");
			System.out.println(stmt.toString());

		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
