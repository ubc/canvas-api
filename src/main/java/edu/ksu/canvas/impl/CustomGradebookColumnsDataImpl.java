package edu.ksu.canvas.impl;

import edu.ksu.canvas.interfaces.CustomGradebookColumnsDataReader;
import edu.ksu.canvas.interfaces.CustomGradebookColumnsDataWriter;
import edu.ksu.canvas.model.ColumnDatum;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;

public class CustomGradebookColumnsDataImpl extends BaseImpl< ColumnDatum, CustomGradebookColumnsDataReader, CustomGradebookColumnsDataWriter >
	implements CustomGradebookColumnsDataReader, CustomGradebookColumnsDataWriter
{
	private static final Logger LOG = LoggerFactory.getLogger( CustomGradebookColumnsDataImpl.class );

	public CustomGradebookColumnsDataImpl( final String canvasBaseUrl, final Integer apiVersion, final OauthToken oauthToken, final RestClient restClient,
		final int connectTimeout, final int readTimeout, final Integer paginationPageSize, final Boolean serializeNulls )
	{
		super( canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls );
	}

	@Override
	protected Type listType( )
	{
		return new TypeToken< List< ColumnDatum > >( ) {}.getType( );
	}

	@Override
	protected Class< ColumnDatum > objectType( )
	{
		return ColumnDatum.class;
	}

	@Override
	public Optional< ColumnDatum > updateColumnData( final String courseId, final String columnId, final ColumnDatum columnDatum ) throws IOException
	{
		LOG.debug( "updating column datum" );
		final String url = buildCanvasUrl( "courses/" + courseId+ "/custom_gradebook_columns/" + columnId + "/data/" + columnDatum.getUserId( ), Collections.emptyMap( ) );
		final Response response = canvasMessenger.putToCanvas( oauthToken, url, columnDatum.toPostMap( serializeNulls ) );
		if( response.getErrorHappened( ) || response.getResponseCode( ) != 200 ) {
			LOG.debug( "Failed to update custom gradebook column data, error message: {}", response );
			return Optional.empty( );
		}
		return responseParser.parseToObject( ColumnDatum.class, response );
	}
}
