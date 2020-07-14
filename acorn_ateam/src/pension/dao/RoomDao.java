package pension.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pension.dto.RoomDto;
import pension.util.DbcpBean;

public class RoomDao {

	private static RoomDao dao;
	private RoomDao() {}
	
	// ??Όλ©? λ§λ¦
	public static RoomDao getInstance() {
		if(dao==null) {
			dao=new RoomDao();
		}
		return dao;
	}
	
	// λ°? λͺ©λ‘ κ°?? Έ?€? μ½λ
	public List<RoomDto> getList(){
		List<RoomDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=new DbcpBean().getConn();
			String sql="SELECT *"
					+ " FROM db_room"
					+ " ORDER BY room_num ASC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				RoomDto dto=new RoomDto();
				dto.setRoom_num(rs.getInt("room_num"));
				dto.setRoom_isUse(rs.getInt("room_isUse"));
				
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return list;
	}
	
	
	
}









