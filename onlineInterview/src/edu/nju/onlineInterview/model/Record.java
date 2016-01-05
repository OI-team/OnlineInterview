package edu.nju.onlineInterview.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author mzdong E-mail:mzdong163.com
 * @description the basic information of interview.
 * ע:��student�����������֪����ô������ʱϹ�㡣
 * @date 2015��12��8�� ����3:11:57
 * @vesion 1.0
 */

@Entity
@Table(name="record", catalog="interview")
public class Record implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1194589016958455855L;
	
	public static final String R_ID = "r_id";
	/** Foreign key to connect table student*/
	public static final String STU_ID = "s_id";
	public static final String R_SCORE = "r_score";
	public static final String R_COMMENT = "r_comment";
	/** the address of storing interviewing snap */
	public static final String R_SNAP = "r_snap";
	/** the address of storing interviewing video */
	public static final String R_VIDEO = "r_video";
	
	private Integer id;
	private Integer s_id;
	private Float score;
	private String comment;
	private String snap;
	private String video;
	
	public Record(){
		
	}
	
	public Record(Integer _id, Integer _sid, Float _score, String _comment, String _snap, String _video){
		this.id = _id;
		this.s_id = _sid;
		this.score = _score;
		this.comment = _comment;
		this.snap = _snap;
		this.video = _video;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name=R_ID, unique=true, nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name=STU_ID, nullable=false)
	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	
	@Column(name=R_SCORE)
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
	@Column(name=R_COMMENT)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name=R_SNAP)
	public String getSnap() {
		return snap;
	}

	public void setSnap(String snap) {
		this.snap = snap;
	}
	
	@Column(name=R_VIDEO)
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	
}
