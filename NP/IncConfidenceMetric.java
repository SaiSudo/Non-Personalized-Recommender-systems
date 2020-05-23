/**
 * Compute the similarity between two items based on increase in confidence
 */ 

package alg.np.similarity.metric;

import java.util.Set;

import profile.Profile;
import util.reader.DatasetReader;

public class IncConfidenceMetric implements SimilarityMetric
{
	private static double RATING_THRESHOLD = 4.0; // the threshold rating for liked items 
	private DatasetReader reader; // dataset reader

	/**
	 * constructor - creates a new IncConfidenceMetric object
	 * @param reader - dataset reader
	 */
	public IncConfidenceMetric(final DatasetReader reader)
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
		// calculate similarity using conf(X => Y) / conf(!X => Y)

		// get the ratings vectors for items X and Y
		Profile pX = reader.getItemProfiles().get(X);
		Profile pY = reader.getItemProfiles().get(Y);

		// calculate supp(X) and supp(!X)
		double suppX = 0;
		double suppNotX = 0;
		for (Integer id: pX.getIds()) // iterate over all users who have rated item X
			if (pX.getValue(id) >= RATING_THRESHOLD)
				suppX += 1;
			else
				suppNotX += 1;

		if (pX.getSize() > 0) { // if no users have rated item X, suppX and suppNotX will be zero
			suppX /= pX.getSize();
			suppNotX /= pX.getSize();
		}

		// calculate supp(X and Y) and supp(!X and Y)
		double suppXandY = 0;
		double suppNotXandY = 0;
		Set<Integer> commonIds = pX.getCommonIds(pY); // get the ids of the users who have rated both items X and Y

		if (commonIds.size() > 0) { // if false, suppXandY and suppNotXandY will be zero
			for (Integer id: commonIds) { // iterate over all users who have rated both items X and Y
				if (pX.getValue(id) >= RATING_THRESHOLD && pY.getValue(id) >= RATING_THRESHOLD)
					suppXandY += 1;

				if (pX.getValue(id) < RATING_THRESHOLD && pY.getValue(id) >= RATING_THRESHOLD)
					suppNotXandY += 1;
			}

			int denom = pX.getSize() + pY.getSize() - commonIds.size();
			suppXandY /= denom;
			suppNotXandY /= denom;
		}

		// calculate conf(X => Y) - if division by zero occurs, set to zero
		double confXimpY = (suppX > 0) ? suppXandY * 1.0 / suppX : 0;

		// calculate conf(!X => Y) - if division by zero occurs, set to zero
		double confNotXimpY = (suppNotX > 0) ? suppNotXandY * 1.0 / suppNotX : 0;
		
		// return the similarity (the increase in liking Y if X is liked)
		// if division by zero occurs, return zero
		return (confNotXimpY > 0) ? confXimpY / confNotXimpY : 0;
	}
}
