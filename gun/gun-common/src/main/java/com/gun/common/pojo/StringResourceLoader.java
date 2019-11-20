package com.gun.common.pojo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

/**
 * Purpose: StringResourceLoader
 * @author MooreChen
 * @since  JDK 1.5
 * @date   2014/9/24
 * @MaintenancePersonnel MooreChen
 */
public class StringResourceLoader extends ResourceLoader {

	 /** Key to look up the repository char encoding. */
    public static final String REPOSITORY_ENCODING = "repository.encoding";

    /** The default repository encoding. */
    public static final String REPOSITORY_ENCODING_DEFAULT = "UTF-8";
    
    private static String charset = null;
    
	public void init(ExtendedProperties configuration) {
		log.info("StringResourceLoader : initialization starting.");

        String encoding = configuration.getString(REPOSITORY_ENCODING, REPOSITORY_ENCODING_DEFAULT);
        if (encoding == null) {
        	charset = REPOSITORY_ENCODING_DEFAULT;
        } else {
        	charset = encoding;
        }

        log.info("StringResourceLoader : initialization complete.");
	}

	public synchronized InputStream getResourceStream(String templateString)
			throws ResourceNotFoundException {
		InputStream result = null;

		if (templateString == null || templateString.length() == 0) {
			throw new ResourceNotFoundException(
					"No   template   string   provided");
		}
		
		try {
			result = new ByteArrayInputStream(templateString.getBytes(charset));
			return result;
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}

	public boolean isSourceModified(Resource resource) {
		return false;
	}

	public long getLastModified(Resource resource) {
		return 0;
	}

}
