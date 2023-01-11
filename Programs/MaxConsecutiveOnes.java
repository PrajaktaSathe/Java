class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
		//To store the result
        int max  = 0;
		
		//StartCount when 0 is found as 0
        int maxCount = 0;
		
		//Flag to check, first one is encountered or not
        boolean foundOne = false;
		
		//Iterate each element
        for(int i:nums){
            if(i==1){
                if(!foundOne) 
                {
                    foundOne = !foundOne;
                    maxCount = 1;
                }
                else
                {
                    maxCount ++;
                }
            }
            else 
            {
				//Increase if found the max consecutive of 1 so far
                max = maxCount>max?maxCount:max;
				
				//Reset flag as false, since at this place we have encountered 0,
				// reset maxCount as 0, since we need to again look for the next bigger consecutive 1
                foundOne = false;
                maxCount = 0;
            }
        }
		//Check at the end if any bigger consecutive 1 is found ( and 0 is not encountered at the end)
        max = maxCount>max?maxCount:max;
        return max;
    }
}