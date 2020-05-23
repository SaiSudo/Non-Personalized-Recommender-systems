/**
 * Compute the similarity between two items based on the Cosine between item ratings
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class RatingMetric implements SimilarityMetric
{
	private DatasetReader reader; // dataset reader

	/**
	 * constructor - creates a new RatingMetric object
	 * @param reader - dataset reader
	 */
	public RatingMetric(final DatasetReader reader)
	{
		this.reader = reader;
	}

	/**
	 * computes the similarity between items
	 * @param X - the id of the first item 
	 * @param Y - the id of the second item
	 */
	public double getItemSimilarity(final Integer X, final Integer Y)
	{
		// calculate similarity using Cosine

		// get the ratings vectors for items X and Y
		Profile pX = reader.getItemProfiles().get(X);
		Profile pY = reader.getItemProfiles().get(Y);

		// get the ids of the users who have rated both items X and Y
		Set<Integer> commonIds = pX.getCommonIds(pY); 		
		
		// iterate over these users and calulate the dot product
		double dotProduct = 0;
		for(Integer id: commonIds)
		{
			// get the ratings assigned by the current user to items X and Y
			double rX = pX.getValue(id);
			double rY = pY.getValue(id);

			// update the dot product
			dotProduct += rX * rY;
		}

		// get the norms of the ratings vectors 
		double n1 = pX.getNorm(); 
		double n2 = pY.getNorm();
		
		// calculate and return the Cosine similarity - if division by zero occurs, return zero
		return (n1 > 0 && n2 > 0) ? dotProduct / (n1 * n2) : 0;
	}
}
