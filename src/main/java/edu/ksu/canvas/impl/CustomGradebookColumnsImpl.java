package edu.ksu.canvas.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import org.slf4j.*;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.CustomColumn;
import edu.ksu.canvas.net.*;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.BaseOptions;

public class CustomGradebookColumnsImpl extends BaseImpl< CustomColumn, CustomGradebookColumnsReader, CustomGradebookColumnsWriter >
	implements CustomGradebookColumnsReader, CustomGradebookColumnsWriter
{
	private static final Logger LOG = LoggerFactory.getLogger( CustomGradebookColumnsImpl.class );

	public CustomGradebookColumnsImpl( final String canvasBaseUrl, final Integer apiVersion, final OauthToken oauthToken, final RestClient restClient,
		final int connectTimeout, final int readTimeout, final Integer paginationPageSize, final Boolean serializeNulls )
	{
		super( canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls );
	}

	@Override
	protected Type listType( )
	{
		return new TypeToken< List< CustomColumn > >( ) {}.getType( );
	}

	@Override
	protected Class< CustomColumn > objectType( )
	{
		return CustomColumn.class;
	}

	@Override
	public List< CustomColumn > listCustomGradebookColumns( final String courseId, final boolean includeHidden ) throws IOException
	{
		LOG.debug( "Getting list of custom gradebook columns by course id" );
		final BaseOptions options = new BaseOptions( ) { { addSingleItem( "include_hidden", Boolean.toString( includeHidden ) ); } };
		final String url = buildCanvasUrl( "courses/" + courseId + "/custom_gradebook_columns", options.getOptionsMap( ) );
		return getListFromCanvas( url );
	}

	@Override
	public Optional< CustomColumn > createCustomGradebookColumn( final String courseId, final CustomColumn column ) throws IOException
	{
		LOG.debug( "creating custom gradebook column" );
		final String url = buildCanvasUrl( "courses/" + courseId + "/custom_gradebook_columns", Collections.emptyMap( ) );
		final Response response = canvasMessenger.sendToCanvas( oauthToken, url, column.toPostMap( serializeNulls ) );
		if( response.getErrorHappened( ) || response.getResponseCode( ) != 200 ) {
			LOG.debug( "Failed to create custom gradebook column, error message: {}", response );
			return Optional.empty( );
		}
		return responseParser.parseToObject( CustomColumn.class, response );
	}
}
