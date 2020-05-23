/**
 * Compute the similarity between two items based on the weighted Jaccard between item genome scores
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class GenomeMetric implements SimilarityMetric
{
	private DatasetReader reader; // dataset reader
	
	/**
	 * constructor - creates a new GenomeMetric object
	 * @param reader - dataset reader
	 */
	public GenomeMetric(final DatasetReader reader)
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
		// calculate similarity using weighted Jaccard
		
		// get the genome scores vectors for items X and Y
		Profile pX = reader.getItemGenomeScores().get(X);
		Profile pY = reader.getItemGenomeScores().get(Y);
		
		// get the ids of the tags
		Set<Integer> tagIds = pX.getIds(); 		
		
		// iterate over the tags and calulate the similarity
		double num = 0;
		double denom = 0;
		for(Integer id: tagIds)
		{
			// get the genome scores for the current tag for items X and Y
			double sX = pX.getValue(id);
			double sY = pY.getValue(id);

			// update the numerator and denominator
			num += Math.min(sX, sY);
			denom += Math.max(sX, sY);
		}
		
		// calculate and return the similarity - if division by zero occurs, return zero
		return (denom > 0) ? num / denom : 0;
	}
}
