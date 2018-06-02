package cn.edu.imu.migration.commons;

import java.util.List;
import java.util.ArrayList;

import cn.edu.imu.migration.entity.Column;
import cn.edu.imu.migration.entity.Task;
import cn.edu.imu.migration.service.TaskService;
import cn.edu.imu.migration.service.impl.TaskServiceImpl;

public class InitTasks {
	/**
	 * 初始化本项目给出的具体任务列表
	 * @return
	 */
	public List<Task> initTasks(){
		List<Task> list = new ArrayList<Task>();
		//小区信息表
		Task task = new Task();
		List<String> sqls = new ArrayList<String>();
		String sql = "SELECT QHBM XQBM, QHMC XQMC, SJQHBM SSQH, SJQHBM DJDW, "+
						"NULL BGR, TBRQ BGRQ, '1' FLAG FROM QH WHERE QHJB = '6'";
		sqls.add(sql);
		List<Column> cols = new ArrayList<Column>();
		Column col1 = new Column(1, "XQBM", "String");
		Column col2 = new Column(2, "XQMC", "String");
		Column col3 = new Column(3, "SSQH", "String");
		Column col4 = new Column(4, "DJDW", "String");
		Column col5 = new Column(5, "BGR", "String");
		Column col6 = new Column(6, "BGRQ_DATE", "Date");
		Column col7 = new Column(7, "Flag", "String");
		cols.add(col1);
		cols.add(col2);
		cols.add(col3);
		cols.add(col4);
		cols.add(col5);
		cols.add(col6);
		cols.add(col7);
		task.setColumns(cols);
		task.setId(1);
		task.setFile("T_XQXX.TXT");
		task.setSql(sqls);

		list.add(task);
		
		//人员基本信息表
		Task task1 = new Task();
		List<String> sqls1 = new ArrayList<String>();
		String sql1 = "SELECT RID, PYXM, NULL, WHCD, ZZMM, ZJXY, NULL, HYZK, NULL, CHRQ,"+
						"SBZK, JKZK, ZY, GZDWMC, SUBSTR(LXDH,1,20), FQBM, FQXM, FQZJLX, FQZJHM, MQBM,"+
						"MQXM, MQZJLX, MQZJHM, POBM, POXM, POZJLX, POZJHM, HKXZ, HKBH, YHZGX,"+
						"HJDXZ, NULL, NULL, JZXZ, XJDXZ, NULL, NULL, NULL, NULL, NULL,"+
						"NULL, NULL, FMLDSZRQ, BRLDSZRQ, TCRQ, TCYY, NULL, NULL, TBRQ, NULL,"+
						"SFYLFN, SFDSZN, HZBM, FHBZ, REMARKS, XM, CSRQ, ZJLX, ZJHM, XB,"+
						"MZ, NULL, SUBSTR(HJDBM,1,12), NULL, SUBSTR(XJDBM,1,12), SUBSTR(DJDWBM,1,12), NULL, rtrim(TBR), EDITLRQ, BZ,"+
						"NULL, NULL, NULL, BYZK, NULL, NULL, NULL, NULL, NULL, XYNHS,"+
						"XYNVHS, NULL, NULL FROM RY";
		sqls1.add(sql1);
		List<Column> cols1 = new ArrayList<Column>();
		Column col11 = new Column(1, "RYBM", "String");
		Column col12 = new Column(2,"XMPY", "String");
		Column col13 = new Column(3, "GLDW", "String");
		Column col14 = new Column(4,"WHCD_DM", "String");
		Column col15 = new Column(5, "ZZMM_DM", "String");
		Column col16 = new Column(6, "ZJXY_DM", "String");
		Column col17 = new Column(7, "DQSX", "String");
		Column col18 = new Column(8, "HYZK_DM", "String");
		Column col19 = new Column(9, "HBRQ_DATE", "Date");
		Column col110 = new Column(10, "CHRQ_DATE", "Date");
		Column col111 = new Column(11, "SBZK", "String");
		Column col112 = new Column(12, "JKZK_DM", "String");
		Column col113 = new Column(13, "ZY_DM", "String");
		Column col114 = new Column(14, "GZDW", "String");
		Column col115 = new Column(15, "LXDH", "String");
		Column col116 = new Column(16, "FQBM", "String");
		Column col117 = new Column(17, "FQXM", "String");
		Column col118 = new Column(18, "FQZJLX_DM", "String");
		Column col119 = new Column(19, "FQZJHM", "String");
		Column col120 = new Column(20, "MQBM", "String");
		Column col121 = new Column(21, "MQXM", "String");
		Column col122 = new Column(22, "MQZJLX_DM", "String");
		Column col123 = new Column(23, "MQZJHM", "String");
		Column col124 = new Column(24, "POBM", "String");
		Column col125 = new Column(25, "POXM", "String");
		Column col126 = new Column(26, "POZJLX_DM", "String");
		Column col127 = new Column(27, "POZJHM", "String");
		Column col128 = new Column(28, "HKXZ_DM", "String");
		Column col129 = new Column(29, "HKBH", "String");
		Column col130 = new Column(30, "YHZGX_DM", "String");
		Column col131 = new Column(31, "HJDXZ", "String");
		Column col132 = new Column(32, "QYRQ_DATE", "String");
		Column col133 = new Column(33, "QYLX_DM", "String");
		Column col134 = new Column(34, "JZXZ_DM", "String");
		Column col135 = new Column(35, "XJDXZ", "String");
		Column col136 = new Column(36, "LDRQ_DATE", "Date");
		Column col137 = new Column(37, "LDLX_DM", "String");
		Column col138 = new Column(38, "LDYY_DM", "String");
		Column col139 = new Column(39, "YZRQ_DATE", "Date");
		Column col140 = new Column(40, "FZRQ_DATE", "Date");
		Column col141 = new Column(41, "ZJJZRQ_DATE", "Date");
		Column col142 = new Column(42, "LDZBH", "String");
		Column col143 = new Column(43, "BRLZRQ_DATE", "Date");
		Column col144 = new Column(44, "ZNLZRQ_DATE", "Date");
		Column col145 = new Column(45, "TCRQ_DATE", "Date");
		Column col146 = new Column(46, "TCYY_DM", "String");
		Column col147 = new Column(47, "JLFZRQ_DATE", "Date");
		Column col148 = new Column(48, "TBFZRQ_DATE", "Date");
		Column col149 = new Column(49, "JKRQ_DATE", "Date");
		Column col150 = new Column(50, "TBR", "String");
		Column col151 = new Column(51, "SFYLFN_DM", "String");
		Column col152 = new Column(52, "SFDSZN_DM", "String");
		Column col153 = new Column(53, "HZBM", "String");
		Column col154 = new Column(54, "FHBZ", "String");
		Column col155 = new Column(55, "BZ", "String");
		Column col156 = new Column(56, "XM", "String");
		Column col157 = new Column(57, "CSRQ_DATE", "Date");
		Column col158 = new Column(58, "ZJLX_DM", "String");
		Column col159 = new Column(59, "ZJHM", "String");
		Column col160 = new Column(60, "XB_DM", "String");
		Column col161 = new Column(61, "MZ_DM", "String");
		Column col162 = new Column(62, "HJD", "String");
		Column col163 = new Column(63, "HJDXZQH", "String");
		Column col164 = new Column(64, "XJZD", "String");
		Column col165 = new Column(65, "XJXZQH", "String");
		Column col166 = new Column(66, "DJDW", "String");
		Column col167 = new Column(67, "DJMC", "String");
		Column col168 = new Column(68, "BGR", "String");
		Column col169 = new Column(69, "BGRQ_DATE", "Date");
		Column col170 = new Column(70, "FLAG", "String");
		Column col171 = new Column(71, "ZMBLZT_DM", "String");
		Column col172 = new Column(72, "HYZMHM", "String");
		Column col173 = new Column(73, "SFLDSZ_DM", "String");
		Column col174 = new Column(74, "DQBYZK_DM", "String");
		Column col175 = new Column(75, "LCRQ_DATE", "Date");
		Column col176 = new Column(76, "LRDDJRQ_DATE", "Date");
		Column col177 = new Column(77, "LDZT_DM", "String");
		Column col178 = new Column(78, "SFTCXT_DM", "String");
		Column col179 = new Column(79, "TCXTYY_DM", "String");
		Column col180 = new Column(80, "NHS", "Int");
		Column col181 = new Column(81, "NVHS", "Int");
		Column col182 = new Column(82, "ZPLJ", "String");
		Column col183 = new Column(83, "LKRQ_DATE", "Date");
		cols1.add(col11);
		cols1.add(col12);
		cols1.add(col13);
		cols1.add(col14);
		cols1.add(col15);
		cols1.add(col16);
		cols1.add(col17);
		cols1.add(col18);
		cols1.add(col19);
		cols1.add(col110);
		cols1.add(col111);
		cols1.add(col112);
		cols1.add(col113);
		cols1.add(col114);
		cols1.add(col115);
		cols1.add(col116);
		cols1.add(col117);
		cols1.add(col118);
		cols1.add(col119);
		cols1.add(col120);
		cols1.add(col121);
		cols1.add(col122);
		cols1.add(col123);
		cols1.add(col124);
		cols1.add(col125);
		cols1.add(col126);
		cols1.add(col127);
		cols1.add(col128);
		cols1.add(col129);
		cols1.add(col130);
		cols1.add(col131);
		cols1.add(col132);
		cols1.add(col133);
		cols1.add(col134);
		cols1.add(col135);
		cols1.add(col136);
		cols1.add(col137);
		cols1.add(col138);
		cols1.add(col139);
		cols1.add(col140);
		cols1.add(col141);
		cols1.add(col142);
		cols1.add(col143);
		cols1.add(col144);
		cols1.add(col145);
		cols1.add(col146);
		cols1.add(col147);
		cols1.add(col148);
		cols1.add(col149);
		cols1.add(col150);
		cols1.add(col151);
		cols1.add(col152);
		cols1.add(col153);
		cols1.add(col154);
		cols1.add(col155);
		cols1.add(col156);
		cols1.add(col157);
		cols1.add(col158);
		cols1.add(col159);
		cols1.add(col160);
		cols1.add(col161);
		cols1.add(col162);
		cols1.add(col163);
		cols1.add(col164);
		cols1.add(col165);
		cols1.add(col166);
		cols1.add(col167);
		cols1.add(col168);
		cols1.add(col169);
		cols1.add(col170);
		cols1.add(col171);
		cols1.add(col172);
		cols1.add(col173);
		cols1.add(col174);
		cols1.add(col175);
		cols1.add(col176);
		cols1.add(col177);
		cols1.add(col178);
		cols1.add(col179);
		cols1.add(col180);
		cols1.add(col181);
		cols1.add(col182);
		cols1.add(col183);
		task1.setColumns(cols1);
		task1.setId(2);
		task1.setFile("T_RYJBXX.TXT");
		task1.setSql(sqls1);

		list.add(task1);
		
		//人员房屋信息表
		Task task2 = new Task();
		List<String> sqls2 = new ArrayList<String>();
		String sql2 = "SELECT NULL, RID "+
						", SUBSTR(DJDWBM,1,12) || SUBSTR(DJDWBM,13,2) || '0'||SUBSTR(DJDWBM,15,2) || SUBSTR(DJDWBM,17,2) || '0'||SUBSTR(DJDWBM,19,2) || SUBSTR(DJDWBM,19,4) FWBM"+
						", '1' RFGX_DM"+
						", SUBSTR(DJDWBM,1,12) DJDW"+
						", RTRIM(TBR)"+
						", EDITLRQ"+
						", BZ FLAG "+
						"FROM RY";
		sqls2.add(sql2);
		List<Column> cols2 = new ArrayList<Column>();
		Column col21 = new Column(1, "BH", "Int", true);
		Column col22 = new Column(2, "RYBM", "String");
		Column col23 = new Column(3, "FWBM", "String");
		Column col24 = new Column(4, "RFGX_DM", "String");
		Column col25 = new Column(5, "DJDW", "String");
		Column col26 = new Column(6, "BGR", "String");
		Column col27 = new Column(7, "BGRQ_DATE", "Date");
		Column col28 = new Column(8, "FLAG", "String");
		cols2.add(col21);
		cols2.add(col22);
		cols2.add(col23);
		cols2.add(col24);
		cols2.add(col25);
		cols2.add(col26);
		cols2.add(col27);
		cols2.add(col28);
		task2.setColumns(cols2);
		task2.setId(3);
		task2.setFile("T_RYFW.TXT");
		task2.setSql(sqls2);
		
		list.add(task2);
		
		//处罚信息表
		Task task3 = new Task();
		List<String> sqls3 = new ArrayList<String>();
		String sql3 = "SELECT RID, RYBM, NULL, ZSYY, YZJE, ZSRQ, JFXS, ZSJE, NULL, ZJRQ,"+
						"DYCL, GBCL, ZSFS, ZFDW, NULL, WFHC, SBRQ, SUBSTR(DJDWBM,1,12), RTRIM(TBR), TBRQ,"+
						"BZ FROM FYF ORDER BY FYF.RID";
		sqls3.add(sql3);
		List<Column> cols3 = new ArrayList<Column>();
		Column col31 = new Column(1, "BH", "String");
		Column col32 = new Column(2, "RYBM", "String");
		Column col33 = new Column(3, "ZJHM", "String");
		Column col34 = new Column(4, "ZSYY_DM", "String");
		Column col35 = new Column(5, "YZJE", "Float");
		Column col36 = new Column(6, "ZSRQ_DATE", "Date");
		Column col37 = new Column(7, "JFXS_DM", "String");
		Column col38 = new Column(8, "ZSJE", "Float");
		Column col39 = new Column(9, "LJZS", "Float");
		Column col310 = new Column(10, "ZJRQ_DATE", "Date");
		Column col311 = new Column(11, "DYCL_DM", "String");
		Column col312 = new Column(12, "GBCL_DM", "String");
		Column col313 = new Column(13, "ZSCX_DM", "String");
		Column col314 = new Column(14, "ZSDW", "String");
		Column col315 = new Column(15, "CFZT", "String");
		Column col316 = new Column(16, "ZCWHC", "String");
		Column col317 = new Column(17, "SBRQ_DATE", "Date");
		Column col318 = new Column(18, "DJDW", "String");
		Column col319 = new Column(19, "BGR", "String");
		Column col320 = new Column(20, "BGRQ_DATE", "Date");
		Column col321 = new Column(21, "FLAG", "String");
		
		cols3.add(col31);
		cols3.add(col32);
		cols3.add(col33);
		cols3.add(col34);
		cols3.add(col35);
		cols3.add(col36);
		cols3.add(col37);
		cols3.add(col38);
		cols3.add(col39);
		cols3.add(col310);
		cols3.add(col311);
		cols3.add(col312);
		cols3.add(col313);
		cols3.add(col314);
		cols3.add(col315);
		cols3.add(col316);
		cols3.add(col317);
		cols3.add(col318);
		cols3.add(col319);
		cols3.add(col320);
		cols3.add(col321);
		task3.setColumns(cols3);
		task3.setId(4);
		task3.setFile("T_CFXX.TXT");
		task3.setSql(sqls3);
		
		list.add(task3);
		
		//避孕状况表
		Task task4 = new Task();
		List<String> sqls4 = new ArrayList<String>();
		String sql4 = "SELECT RID, RYBM, NULL, BYKSRQ, BYSSJG, BYZK, BYZZRQ, BYZZJG, NULL, SUBSTR(DJDWBM,1,12),"+
						"RTRIM(TBR), TBRQ, BZ FROM BYXX ORDER BY BYXX.RID";
		sqls4.add(sql4);
		List<Column> cols4 = new ArrayList<Column>();
		Column col41 = new Column(1, "BH", "String");
		Column col42 = new Column(2, "RYBM", "String");
		Column col43 = new Column(3, "ZJHM", "String");
		Column col44 = new Column(4, "BYKSRQ_DATE", "Date");
		Column col45 = new Column(5, "BYSSJG_DM", "String");
		Column col46 = new Column(6, "BYZK_DM", "String");
		Column col47 = new Column(7, "BYZZRQ_DATE", "Date");
		Column col48 = new Column(8, "ZZBYJG_DM", "String");
		Column col49 = new Column(9, "SBRQ_DATE", "Date");
		Column col410 = new Column(10, "DJDW", "String");
		Column col411 = new Column(11, "BGR", "String");
		Column col412 = new Column(12, "BGRQ_DATE", "Date");
		Column col413 = new Column(13, "FLAG", "String");
		
		cols4.add(col41);
		cols4.add(col42);
		cols4.add(col43);
		cols4.add(col44);
		cols4.add(col45);
		cols4.add(col46);
		cols4.add(col47);
		cols4.add(col48);
		cols4.add(col49);
		cols4.add(col410);
		cols4.add(col411);
		cols4.add(col412);
		cols4.add(col413);
		task4.setColumns(cols4);
		task4.setId(5);
		task4.setFile("T_BYZK.TXT");
		task4.setSql(sqls4);
		
		list.add(task4);
		
		//查体情况表
		Task task5 = new Task();
		List<String> sqls5 = new ArrayList<String>();
		String sql5 = "SELECT NULL, RYBM, NULL ZJHM, YQJC, JYQJC, SZJKJC, JCRQ, JCJG, SBRQ, "+
						"SUBSTR(DJDWBM,1,12), RTRIM(TBR), TBRQ, BZ FROM CT ORDER BY CT.RID";
		sqls5.add(sql5);
		List<Column> cols5 = new ArrayList<Column>();
		Column col51 = new Column(1, "BH", "Int", true);
		Column col52 = new Column(2, "RYBM", "String");
		Column col53 = new Column(3, "ZJHM", "String");
		Column col54 = new Column(4, "YQJC_DM", "String");
		Column col55 = new Column(5, "JYQJC_DM", "String");
		Column col56 = new Column(6, "SZJKJC_DM", "String");
		Column col57 = new Column(7, "JCRQ_DATE", "Date");
		Column col58 = new Column(8, "SSJG_DM", "String");
		Column col59 = new Column(9, "SBRQ_DATE", "Date");
		Column col510 = new Column(10, "DJDW", "String");
		Column col511 = new Column(11, "BGR", "String");
		Column col512 = new Column(12, "BGRQ_DATE", "Date");
		Column col513 = new Column(13, "FLAG", "String");
		
		cols5.add(col51);
		cols5.add(col52);
		cols5.add(col53);
		cols5.add(col54);
		cols5.add(col55);
		cols5.add(col56);
		cols5.add(col57);
		cols5.add(col58);
		cols5.add(col59);
		cols5.add(col510);
		cols5.add(col511);
		cols5.add(col512);
		cols5.add(col513);
		task5.setColumns(cols5);
		task5.setId(6);
		task5.setFile("T_CTQK.TXT");
		task5.setSql(sqls5);

		list.add(task5);
		
		//生养信息表
		Task task6 = new Task();
		List<String> sqls6 = new ArrayList<String>();
		String sql6 = "SELECT RID, RYBM, NULL, ZNBM, ZNXM, ZNHC, ZNXB, ZCSX, WFYY, ZNZJHM,"+
						"ZNCSRQ, ZNHK, XYGX, SYZK, CSZK, DQZK, ZNSWRQ, LDSZRQ, NULL, NULL,"+
						"FQBM, FQXM, FQZJHM, MQBM, MQXM, MQZJHM, NULL, NULL, NULL, SUBSTR(CSDBM,1,12),"+
						"CSDXZ, SBRQ, SUBSTR(DJDWBM,1,12), RTRIM(TBR), EDITLRQ, BZ "+
						"FROM SY ORDER BY SY.RID";
		sqls6.add(sql6);
		List<Column> cols6 = new ArrayList<Column>();
		Column col61 = new Column(1, "BH", "String");
		Column col62 = new Column(2, "RYBM", "String");
		Column col63 = new Column(3, "ZJHM", "String");
		Column col64 = new Column(4, "ZNBM", "String");
		Column col65 = new Column(5, "ZNXM", "String");
		Column col66 = new Column(6, "ZNHC", "String");
		Column col67 = new Column(7, "ZNXB_DM", "String");
		Column col68 = new Column(8, "ZCSX_DM", "String");
		Column col69 = new Column(9, "WFYY_DM", "String");
		Column col610 = new Column(10, "ZNSFZHM", "String");
		Column col611 = new Column(11, "ZNCSRQ_DATE", "Date");
		Column col612 = new Column(12, "ZNHKXZ_DM", "String");
		Column col613 = new Column(13, "XYGX_DM", "String");
		Column col614 = new Column(14, "SYBZ_DM", "String");
		Column col615 = new Column(15, "CSJKZK_DM", "String");
		Column col616 = new Column(16, "DQJKZK_DM", "String");
		Column col617 = new Column(17, "SWRQ_DATE", "Date");
		Column col618 = new Column(18, "LDSZRQ_DATE", "Date");
		Column col619 = new Column(19, "FWZH", "String");
		Column col620 = new Column(20, "FZRQ_DATE", "Date");
		Column col621 = new Column(21, "SFBM", "String");
		Column col622 = new Column(22, "SFXM", "String");
		Column col623 = new Column(23, "SFZJHM", "String");
		Column col624 = new Column(24, "SMBM", "String");
		Column col625 = new Column(25, "SMXM", "String");
		Column col626 = new Column(26, "SMZJHM", "String");
		Column col627 = new Column(27, "FYKSRQ_DATE", "Date");
		Column col628 = new Column(28, "FYKJSRQ_DATE", "Date");
		Column col629 = new Column(29, "FYKJSYY_DM", "String");
		Column col630 = new Column(30, "ZNCSDXZQH", "String");
		Column col631 = new Column(31, "ZNCSDXZ", "String");
		Column col632 = new Column(32, "SBRQ_DATE", "Date");
		Column col633 = new Column(33, "DJDW", "String");
		Column col634 = new Column(34, "BGR", "String");
		Column col635 = new Column(35, "BGRQ_DATE", "Date");
		Column col636 = new Column(36, "FLAG", "String");
		cols6.add(col61);
		cols6.add(col62);
		cols6.add(col63);
		cols6.add(col64);
		cols6.add(col65);
		cols6.add(col66);
		cols6.add(col67);
		cols6.add(col68);
		cols6.add(col69);
		cols6.add(col610);
		cols6.add(col611);
		cols6.add(col612);
		cols6.add(col613);
		cols6.add(col614);
		cols6.add(col615);
		cols6.add(col616);
		cols6.add(col617);
		cols6.add(col618);
		cols6.add(col619);
		cols6.add(col620);
		cols6.add(col621);
		cols6.add(col622);
		cols6.add(col623);
		cols6.add(col624);
		cols6.add(col625);
		cols6.add(col626);
		cols6.add(col627);
		cols6.add(col628);
		cols6.add(col629);
		cols6.add(col630);
		cols6.add(col631);
		cols6.add(col632);
		cols6.add(col633);
		cols6.add(col634);
		cols6.add(col635);
		cols6.add(col636);
		task6.setColumns(cols6);
		task6.setId(7);
		task6.setFile("T_SYXX.TXT");
		task6.setSql(sqls6);
		
		list.add(task6);
		//妊辰信息表
		Task task7 = new Task();
		List<String> sqls7 = new ArrayList<String>();
		String sql7 = "SELECT RID, RYBM, NULL, YC, NULL, NULL, MCYJRQ, ZCSX, RSZZRQ, RSZZDD,"+
						"RSJG, RLYY, SBRQ, SUBSTR(DJDWBM,1,12), RTRIM(TBR), TBRQ, '1' "+
						"FROM RS ORDER BY RS.RID";
		sqls7.add(sql7);
		List<Column> cols7 = new ArrayList<Column>();
		Column col71 = new Column(1, "BH", "String");
		Column col72 = new Column(2, "RYBM", "String");
		Column col73 = new Column(3, "ZJHM", "String");
		Column col74 = new Column(4, "YC", "String");
		Column col75 = new Column(5, "FXHYRQ_DATE", "Date");
		Column col76 = new Column(6, "FXHYZS", "String");
		Column col77 = new Column(7, "MCYJRQ_DATE", "Date");
		Column col78 = new Column(8, "HYZCSX_DM", "String");
		Column col79 = new Column(9, "RSZZRQ_DATE", "Date");
		Column col710 = new Column(10, "RSZZDD_DM", "String");
		Column col711 = new Column(11, "RSJG_DM", "String");
		Column col712 = new Column(12, "RLYY_DM", "String");
		Column col713 = new Column(13, "SBRQ_DATE", "Date");
		Column col714 = new Column(14, "DJDW", "String");
		Column col715 = new Column(16, "BGR", "String");
		Column col716 = new Column(17, "BGRQ_DATE", "Date");
		Column col717 = new Column(18, "FLAG", "String");
		cols7.add(col71);
		cols7.add(col72);
		cols7.add(col73);
		cols7.add(col74);
		cols7.add(col75);
		cols7.add(col76);
		cols7.add(col77);
		cols7.add(col78);
		cols7.add(col79);
		cols7.add(col710);
		cols7.add(col711);
		cols7.add(col712);
		cols7.add(col713);
		cols7.add(col714);
		cols7.add(col715);
		cols7.add(col716);
		cols7.add(col717);
		task7.setColumns(cols7);
		task7.setId(8);
		task7.setFile("T_RSXX.TXT");
		task7.setSql(sqls7);
		
		list.add(task7);
		
		//奖励信息表
		Task task8 = new Task();
		List<String> sqls8 = new ArrayList<String>();
		String sql8 = "SELECT RID, RYBM, NULL, NULL, NULL, JLYY, JLRQ, SUBSTR(JLXS,1,1), JLJE, NULL,"+
						"NULL, NULL, NULL, ZH, SBRQ, SUBSTR(DJDWBM,1,12), RTRIM(TBR), TBRQ, BZ "+
						"FROM JL ORDER BY JL.RID";
		sqls8.add(sql8);
		List<Column> cols8 = new ArrayList<Column>();
		Column col81 = new Column(1, "BH", "String");
		Column col82 = new Column(2, "RYBM", "String");
		Column col83 = new Column(3, "ZJHM", "String");
		Column col84 = new Column(4, "LZRQ_DATE", "Date");
		Column col85 = new Column(5, "JLZJHM", "String");
		Column col86 = new Column(6, "JLYY_DM", "String");
		Column col87 = new Column(7, "JLRQ_DATE", "Date");
		Column col88 = new Column(8, "JLXS_DM", "String");
		Column col89 = new Column(9, "JLJE", "Float");
		Column col810 = new Column(10, "LJJE", "String");
		Column col811 = new Column(11, "WGRQ_DATE", "Date");
		Column col812 = new Column(12, "TZRQ_DATE", "Date");
		Column col813 = new Column(13, "TJJE", "String");
		Column col814 = new Column(14, "ZH", "String");
		Column col815 = new Column(15, "SBRQ_DATE", "Date");
		Column col816 = new Column(16, "DJDW", "String");
		Column col817 = new Column(17, "BGR", "String");
		Column col818 = new Column(18, "BGRQ_DATE", "Date");
		Column col819 = new Column(19, "FLAG", "String");
		cols8.add(col81);
		cols8.add(col82);
		cols8.add(col83);
		cols8.add(col84);
		cols8.add(col85);
		cols8.add(col86);
		cols8.add(col87);
		cols8.add(col88);
		cols8.add(col89);
		cols8.add(col810);
		cols8.add(col811);
		cols8.add(col812);
		cols8.add(col813);
		cols8.add(col814);
		cols8.add(col815);
		cols8.add(col816);
		cols8.add(col817);
		cols8.add(col818);
		cols8.add(col819);
		task8.setColumns(cols8);
		task8.setId(9);
		task8.setFile("T_JLXX.TXT");
		task8.setSql(sqls8);

		list.add(task8);
		
		//房屋信息表
		Task task9 = new Task();
		List<String> sqls9 = new ArrayList<String>();
		String sql9 = "SELECT T5.SJQHBM || SUBSTR(T1.QHBM,13,2) || '0'||SUBSTR(T1.QHBM,15,2) || SUBSTR(T1.QHBM,17,2) || '0'||SUBSTR(T1.QHBM,19,2) || SUBSTR(T1.QHBM,19,4) FWBH"+
						", '1' FWLX_DM "+
						", '0'||SUBSTR(T1.QHBM,15,2) LP, SUBSTR(T1.QHBM,17,2) DYL, '0'||SUBSTR(T1.QHBM,19,2) CY, SUBSTR(T1.QHBM,19,4) MPH"+
						", SUBSTR(T4.QHMC,1,10) LPXZ, T3.QHMC DYLXZ, T2.QHMC CYXZ, T1.QHMC MPHXZ"+
						", T5.SJQHBM SZXQ, T5.SJQHBM DJDW, NULL BGR, SYSDATE BGRQ, '1' FLAG FROM QH T1 "+
						"LEFT JOIN QH T2 ON T1.SJQHBM = T2.QHBM LEFT JOIN QH T3 ON T2.SJQHBM = T3.QHBM "+
						"LEFT JOIN QH T4 ON T3.SJQHBM = T4.QHBM LEFT JOIN QH T5 ON T4.SJQHBM = T5.QHBM "+
						"WHERE T1.QHJB = '10' AND T2.QHJB = '9' AND T3.QHJB = '8' AND T4.QHJB = '7' AND T5.QHJB = '6'";
		sqls9.add(sql9);
		List<Column> cols9 = new ArrayList<Column>();
		Column col91 = new Column(1, "FWBH", "String");
		Column col92 = new Column(2, "FWLX_DM", "String");
		Column col93 = new Column(3, "LP", "String");
		Column col94 = new Column(4, "DYL", "String");
		Column col95 = new Column(5, "CY", "String");
		Column col96 = new Column(6, "MPH", "String");
		Column col97 = new Column(7, "LPXZ", "String");
		Column col98 = new Column(8, "DYLXZ", "String");
		Column col99 = new Column(9, "CYXZ", "String");
		Column col910 = new Column(10, "MPHXZ", "String");
		Column col911 = new Column(11, "LPXZ", "String");
		Column col912 = new Column(12, "DJDW", "String");
		Column col913 = new Column(13, "BGR", "String");
		Column col914 = new Column(14, "BGR_DATE", "Date");
		Column col915 = new Column(15, "FLAG", "String");
		
		cols9.add(col91);
		cols9.add(col92);
		cols9.add(col93);
		cols9.add(col94);
		cols9.add(col95);
		cols9.add(col96);
		cols9.add(col97);
		cols9.add(col98);
		cols9.add(col99);
		cols9.add(col910);
		cols9.add(col911);
		cols9.add(col912);
		cols9.add(col913);
		cols9.add(col914);
		cols9.add(col915);
		task9.setColumns(cols9);
		task9.setId(10);
		task9.setFile("T_FWXX.TXT");
		task9.setSql(sqls9);

		list.add(task9);
		
		return list;
	}
}
