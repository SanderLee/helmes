package ee.leemet.helmes.service;

import ee.leemet.helmes.entity.Sector;
import ee.leemet.helmes.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SectorService {

	private SectorRepository sectorRepository;

	@Autowired
	public SectorService(SectorRepository sectorRepository) {
		this.sectorRepository = sectorRepository;
	}

	public Collection<Sector> getSectors() {
		return sectorRepository.getSectors();
	}
}
