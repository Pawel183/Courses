export type RecipeIngredient = {
  name: string;
  quantity: string;
};

// export type Recipe = string;

export type Recipe = {
  title: string;
  description: string;
  cookingTimeMinutes: number;
  servings: number;
  ingredients: RecipeIngredient[];
  steps: string[];
  notes: string[];
}