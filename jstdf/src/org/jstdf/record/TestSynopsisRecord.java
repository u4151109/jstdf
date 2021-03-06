package org.jstdf.record;

import java.nio.ByteBuffer;
import java.util.BitSet;

import org.jstdf.util.StdfRecordUtils;


/**
 * Test Synopsis Record (TSR)
 * 
 * @author malong
 *
 */
public class TestSynopsisRecord extends AbstractSTDFRecord
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1768197232034929601L;
	/**
	 * 10
	 */
	public static final byte Type = 10;
	/**
	 * 30
	 */
	public static final byte SubType = 30;
	
	/**
	 * U*1 Test head number
	 */
	public int HEAD_NUM; //U*1 Test head number See note
	/**
	 * U*1 Test site number
	 */
	public int SITE_NUM; //U*1 Test site number
	/**
	 * C*1 Test type space
	 */
	public char TEST_TYP; //C*1 Test type space
	/**
	 * U*4 Test number
	 */
	public long TEST_NUM; //U*4 Test number
	/**
	 * U*4 Number of test executions, 4,294,967,295 if data missing
	 */
	public long EXEC_CNT; //U*4 Number of test executions 4,294,967,295
	/**
	 * U*4 Number of test failures, 4,294,967,295 if data missing
	 */
	public long FAIL_CNT; //U*4 Number of test failures 4,294,967,295
	/**
	 * U*4 Number of alarmed tests, 4,294,967,295 if data missing
	 */
	public long ALRM_CNT; //U*4 Number of alarmed tests 4,294,967,295
	/**
	 * C*n Test name length byte = 0 if data missing
	 */
	public String TEST_NAM; //C*n Test name length byte = 0
	/**
	 * C*n Sequencer (program segment/flow) name length byte = 0 if data missing
	 */
	public String SEQ_NAME; //C*n Sequencer (program segment/flow) name length byte = 0
	/**
	 * C*n Test label or text length byte = 0 if data missing
	 */
	public String TEST_LBL; //C*n Test label or text length byte = 0
	/**
	 * B*1 Optional data flag 
	 */
	public BitSet OPT_FLAG; //B*1 Optional data flag See note
	/**
	 * R*4 Average test execution time in seconds, OPT_FLAG bit 2 = 1 if data invalid
	 */
	public double TEST_TIM; //R*4 Average test execution time in seconds OPT_FLAG bit 2 = 1
	/**
	 * R*4 Lowest test result value, OPT_FLAG bit 0 = 1 if data invalid
	 */
	public double TEST_MIN; //R*4 Lowest test result value OPT_FLAG bit 0 = 1
	/**
	 * R*4 Highest test result value, OPT_FLAG bit 1 = 1 if data invalid
	 */
	public double TEST_MAX; //R*4 Highest test result value OPT_FLAG bit 1 = 1
	/**
	 * R*4 Sumof test result values, OPT_FLAG bit 4 = 1 if data invalid
	 */
	public double TST_SUMS; //R*4 Sumof test result values OPT_FLAG bit 4 = 1
	/**
	 * R*4 Sum of squares of test result values, OPT_FLAG bit 5 = 1 if data invalid
	 */
	public double TST_SQRS; //R*4 Sum of squares of test result values OPT_FLAG bit 5 = 1
	
	public static final TestSynopsisRecord getInstance(int seq, int len, int typ, int sub, ByteBuffer bb)
	{
		TestSynopsisRecord rec = new TestSynopsisRecord();
		rec.setHeadInfo(seq, len, typ, sub);
		rec.readContent(bb);
		return rec;
	}
	
	@Override
	public void readContent(ByteBuffer bb)
	{
		HEAD_NUM = StdfRecordUtils.readU1Int(bb);
		SITE_NUM = StdfRecordUtils.readU1Int(bb);
		TEST_TYP = StdfRecordUtils.readC1Char(bb);
		TEST_NUM = StdfRecordUtils.readU4Int(bb);
		EXEC_CNT = StdfRecordUtils.readU4Int(bb, 4294967295L);
		FAIL_CNT = StdfRecordUtils.readU4Int(bb, 4294967295L);
		ALRM_CNT = StdfRecordUtils.readU4Int(bb, 4294967295L);
		TEST_NAM = StdfRecordUtils.readCnString(bb);
		SEQ_NAME = StdfRecordUtils.readCnString(bb);
		TEST_LBL = StdfRecordUtils.readCnString(bb);
		OPT_FLAG = StdfRecordUtils.readBits(bb, 1);
		
		TEST_TIM = StdfRecordUtils.readR4Double(bb);
		TEST_MIN = StdfRecordUtils.readR4Double(bb);
		TEST_MAX = StdfRecordUtils.readR4Double(bb);
		TST_SUMS = StdfRecordUtils.readR4Double(bb);
		TST_SQRS = StdfRecordUtils.readR4Double(bb);
	}

	@Override
	public STDFRecordType getRecordType()
	{
		return STDFRecordType.TSR;
	}

	@Override
	public String toString() {
		return getRecordNo()+": TestSynopsisRecord [HEAD_NUM=" + HEAD_NUM + ", SITE_NUM="
				+ SITE_NUM + ", TEST_TYP=" + TEST_TYP + ", TEST_NUM="
				+ TEST_NUM + ", EXEC_CNT=" + EXEC_CNT + ", FAIL_CNT="
				+ FAIL_CNT + ", ALRM_CNT=" + ALRM_CNT + ", TEST_NAM="
				+ TEST_NAM + ", SEQ_NAME=" + SEQ_NAME + ", TEST_LBL="
				+ TEST_LBL + ", OPT_FLAG=" + OPT_FLAG + ", TEST_TIM="
				+ TEST_TIM + ", TEST_MIN=" + TEST_MIN + ", TEST_MAX="
				+ TEST_MAX + ", TST_SUMS=" + TST_SUMS + ", TST_SQRS="
				+ TST_SQRS + "]";
	}
}
