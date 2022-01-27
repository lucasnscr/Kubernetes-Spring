package repository;

import java.util.Date;
import java.util.List;
import dto.ShopReportDTO;
import model.Shop;

public interface ReportRepository  {
	
	public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);
	
	public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);

}
