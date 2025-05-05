package es.gob.valet.importsls;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.exceptions.ImporTslsException;
import es.gob.valet.persistence.exceptions.ImportException;

public interface IImporTslService {

	void startProcessImport(MultipartFile tslsFile, boolean overwrite) throws IOException, ImportException;
	
	int getStepProgress(int i);

	boolean isRunning();

	boolean isError();

	int getCurrentStep();
	
	String getMessageError();
	
	int getNumTslImp();
		
	void imporTslsUniqueTransaction() throws ImporTslsException;
	
	void setCurrentStep(int i);

	void disableTslRelatedTask() throws ImporTslsException;

	void enableTslRelatedTask() throws ImporTslsException;
	
	void setMessageError(String messageError);
	
	void setError(boolean isError);
	
	void setRunning(boolean isRunning);
	
	String getSbSummaryImport();
	
	String getListTslDataNotImpByVersionMinor();
	
	int getNumMappingByTslImp();

	int getNumMappingByServImp();

	int getNumTslDataNotImp();

	int getNumMappingByTslNotImp();

	int getNumMappingByServNotImp();
}
